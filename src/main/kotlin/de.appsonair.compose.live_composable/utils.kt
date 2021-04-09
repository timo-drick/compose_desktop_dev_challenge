package de.appsonair.compose.live_composable

import androidx.compose.runtime.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.isActive
import kotlinx.coroutines.withContext
import java.io.File
import java.nio.file.FileSystems
import java.nio.file.StandardWatchEventKinds
import kotlin.reflect.KClass

/**
 * apps on air
 *
 * @author Timo Drick
 */

/**
 * We can not return the class itself because after reloading it is another type.
 * So only an interface can be returned that is loaded in the parent class loader.
 */
@Suppress("UNCHECKED_CAST")
fun <T: I, I: Any>loadClass(path: File, clazz: KClass<T>): I {
    val className = checkNotNull(clazz.qualifiedName)
    val classLoader = CompiledScriptClassLoader(
        parent = clazz.java.classLoader,
        path = path.absoluteFile
    )
    val reloadedClazz = classLoader.loadClass(className).kotlin
    return reloadedClazz.constructors.first().call() as I
}

class CompiledScriptClassLoader(parent: ClassLoader, private val path: File) : ClassLoader(parent) {
    override fun loadClass(name: String, resolve: Boolean): Class<*> = if (class2File(name).exists()) {
        val bytes = class2File(name).readBytes()
        defineClass(name, bytes, 0, bytes.size)
    } else {
        parent.loadClass(name)
    }
    private fun class2File(className: String): File {
        val classFileName = className.replace(".", "/") + ".class"
        return File(path, classFileName)
    }
}

@Composable
fun FileAsState(file: File): String {
    var fileContent by remember(file) { mutableStateOf(file.readText()) }
    LaunchedEffect(file) {
        withContext(Dispatchers.IO) {
            val watchService = FileSystems.getDefault().newWatchService()
            file.parentFile.toPath().register(watchService, StandardWatchEventKinds.ENTRY_MODIFY)
            while (isActive) {
                val watchKey = watchService.take()
                var fileChanged = false
                for (event in watchKey.pollEvents()) {
                    if (event.context().toString() == file.name) fileChanged = true
                    println("event: ${event.kind()} context: ${event.context()} ${event.count()}")
                }
                if (fileChanged) {
                    fileContent = file.readText()
                }
                if (!watchKey.reset()) {
                    watchKey.cancel()
                    watchService.close()
                    break
                }
            }
        }
    }
    return fileContent
}

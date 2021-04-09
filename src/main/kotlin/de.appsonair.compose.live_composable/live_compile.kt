package de.appsonair.compose.live_composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.kotlin.cli.common.arguments.K2JVMCompilerArguments
import org.jetbrains.kotlin.cli.common.messages.MessageRenderer
import org.jetbrains.kotlin.cli.common.messages.PrintingMessageCollector
import org.jetbrains.kotlin.cli.jvm.K2JVMCompiler
import org.jetbrains.kotlin.config.Services
import java.io.File
import kotlin.reflect.KClass

/**
 * apps on air
 *
 * @author Timo Drick
 */

abstract class ILiveComposable() {
    @Composable
    abstract fun start()
}

@Composable
inline fun <reified T: ILiveComposable>LiveComposable(file: File) = LiveComposable<T, ILiveComposable>(file, T::class)
@Composable {
    start()
}

@Composable
inline fun <reified T: I, I: Any>LiveComposable(file: File, noinline block: @Composable I.() -> Unit) = LiveComposable(file, T::class, block)

internal val messageCollector = PrintingMessageCollector(System.err, MessageRenderer.PLAIN_RELATIVE_PATHS, false)

@Composable
fun <T: I, I: Any> LiveComposable(file: File, clazz: KClass<T>, block: @Composable I.() -> Unit) {
    val compilerArgs = remember(file) {
        val classPath = System.getProperty("java.class.path")
            .split(System.getProperty("path.separator"))
            .filter {
                File(it).exists() && File(it).canRead()
            }.joinToString(":")
        val pluginFolder = Thread.currentThread().contextClassLoader.getResource("compilerPlugins")?.file
        val pluginPathList = File(pluginFolder).listFiles().map { it.toString() }.toTypedArray()
        File("runtime").mkdir()
        K2JVMCompilerArguments().apply {
            freeArgs = listOf(file.toString())
            destination = "runtime"
            classpath = classPath
            pluginClasspaths = pluginPathList
            noReflect = true
            noStdlib = true
            jvmTarget = "11"
            useIR = true
            script = false
            noOptimize = true
            noOptimizedCallableReferences = true
            reportPerf = false
        }
    }
    val compiler = remember {
        K2JVMCompiler()
    }
    val fileContent = FileAsState(file)
    var liveObj by remember { mutableStateOf<I?>(null) }
    LaunchedEffect(fileContent, compilerArgs) {
        withContext(Dispatchers.Default) {
            try {
                val startCompile = System.currentTimeMillis()
                compiler.exec(messageCollector, Services.EMPTY, compilerArgs)
                println("Compile time: ${System.currentTimeMillis() - startCompile} ms")
                val startLoading = System.currentTimeMillis()
                liveObj = loadClass<T, I>(File("runtime"), clazz)
                println("Class loading time: ${System.currentTimeMillis() - startLoading} ms")
            } catch (err: Throwable) {
                //err.printStackTrace()
            }
        }
    }
    /*
    // Without any enforced recursive invalidation it does not work at all :-(
    liveObj?.let { block(it) }
    */

    // This method of hot reload swapping is working but lambda functions are not swapped reliable
    /*
    val LocalLiveObj = staticCompositionLocalOf {
        liveObj
    }
    CompositionLocalProvider(LocalLiveObj provides liveObj) {
        LocalLiveObj.current?.let { block(it) }
    }
    */

    // This method of hot reload swapping is working well as far as i tested but the remembered variables get lost.
    key(liveObj) {
        if (liveObj != null) {
            liveObj?.let { block(it) }
        } else {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    CircularProgressIndicator(Modifier.padding(8.dp))
                    Text("Compiling", textAlign = TextAlign.Center)
                }
            }
        }
    }
}
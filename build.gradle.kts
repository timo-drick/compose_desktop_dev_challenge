import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.32"
    id("org.jetbrains.compose") version "0.4.0-build180"
}

group = "com.example.androiddevchallenge"
version = "1.0.0"

repositories {
    jcenter()
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev") }
}

dependencies {
    implementation(compose.desktop.currentOs)
    implementation(compose.material)
    implementation(compose.materialIconsExtended)

    //Live coding dependency
    implementation(kotlin("reflect"))
    implementation(kotlin("compiler-embeddable"))
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}

//Live coding dependency
val generatedResources = "$buildDir/generated-resources/main"
sourceSets {
    main {
        output.dir(generatedResources, "builtBy" to "copyComposeCompiler")
    }
}
tasks.register<Copy>("copyComposeCompiler") {
    val pluginClasspath = configurations.kotlinCompilerPluginClasspath
    val composePluginPath = pluginClasspath.get().files.find { it.toString().contains("org.jetbrains.compose.compiler") }
    println(composePluginPath)

    from(composePluginPath)
    into(file("$generatedResources/compilerPlugins"))
}


compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "AndroidDevchallenge"
            packageVersion = "1.0.0"
        }
    }
}
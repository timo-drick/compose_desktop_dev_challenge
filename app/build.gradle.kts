import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    //alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.plugin.compose)
    alias(libs.plugins.compose.multiplatform)
}

group = "com.example.androiddevchallenge"
version = "1.0.0"

kotlin {

    /*wasmJs {
        moduleName = "composeApp"
        browser {
            commonWebpackConfig {
                outputFileName = "composeApp.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(project.projectDir.path)
                    }
                }
            }
        }
        binaries.executable()
    }*/

    jvm("desktop")

    //androidTarget() //This target is only for nice android preview

    sourceSets {
        val desktopMain by getting
        //val androidMain by getting

        commonMain.dependencies {

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.ui)
            implementation(compose.material)
            implementation(compose.materialIconsExtended)
            implementation(compose.components.resources)

            implementation("de.drick.compose:hotpreview:0.1.0")
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)

        }
    }
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

/*android {
    compileSdk = libs.versions.compileSdk.get().toInt()
    namespace = "de.sevenp.moaps"

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")

    defaultConfig {
        applicationId = "de.sevenp.moaps.PreviewConfig"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }
}*/
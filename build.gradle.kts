plugins {
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.compose.multiplatform) apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.plugin.compose)
    alias(libs.plugins.github.ben.manes.versions)
}

fun isStable(version: String): Boolean {
    val unStableKeyword = listOf("alpha", "beta", "rc", "cr", "m", "preview", "dev").any { version.contains(it, ignoreCase = true) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    return unStableKeyword.not() || regex.matches(version)
}

fun isNonStable(version: String) = isStable(version).not()

tasks.named("dependencyUpdates", com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask::class.java).configure {
    rejectVersionIf {
        (isNonStable(candidate.version) && isStable(currentVersion))
    }
}
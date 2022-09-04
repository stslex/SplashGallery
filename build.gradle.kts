plugins {
    id("com.android.application") version "7.2.2" apply false
    id("com.android.library") version "7.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.6.20" apply false
    kotlin("jvm") version "1.6.20"
    kotlin("plugin.serialization") version "1.6.20"
}

buildscript {
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.40.1")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.5.1")
    }
}

tasks.register(name = "type", type = Delete::class) {
    delete(rootProject.buildDir)
}
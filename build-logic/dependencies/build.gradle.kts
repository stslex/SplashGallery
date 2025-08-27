plugins {
    `kotlin-dsl`
}

group = "com.stslex.splashgallery.buildlogic"

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.android.tools.common)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = libs.plugins.app.android.application.get().pluginId
            implementationClass = "AndroidApplicationPlugin"
        }
        register("androidLibrary") {
            id = libs.plugins.app.android.lib.get().pluginId
            implementationClass = "AndroidLibraryPlugin"
        }
    }
}
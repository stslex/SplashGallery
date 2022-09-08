pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "splashgallery"
include(":app")
include(":core")
include(":core-test")
include(":core-ui")
include(":core-coroutines")
include(":feature-collections")
include(":core-model")
include(":core-navigation")

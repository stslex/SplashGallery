package com.stslex.splashgallery

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

object AppExt {

    const val APP_PREFIX = "com.stslex.splashgallery"

    /**
     * Get the version catalog for the project
     * */
    val Project.libs: VersionCatalog
        get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

    /**
     * Find the version of the library
     */
    fun VersionCatalog.findVersionInt(name: String) = findVersionString(name).toInt()

    /**
     * Find the version of the library
     */
    fun VersionCatalog.findVersionString(name: String) = findVersion(name).get().toString()

    /**
     * Find the plugin id
     */
    fun VersionCatalog.findPluginId(alias: String) = findPlugin(alias).get().get().pluginId

    /**
     * Find the library by alias
     */
    fun Project.implementation(vararg alias: String) {
        dependencies {
            alias.forEach {
                add("implementation", libs.findLibrary(it).get())
            }
        }
    }

    fun Project.debugImplementation(vararg alias: String) {
        dependencies {
            alias.forEach {
                add("debugImplementation", libs.findLibrary(it).get())
            }
        }
    }

    fun Project.implementationPlatform(vararg alias: String) {
        dependencies {
            alias.forEach {
                add(
                    "debugImplementation",
                    platform(libs.findLibrary(it).get())
                )
            }
        }
    }

    /**
     * Find the bundle by alias
     */
    fun Project.implementationBundle(vararg alias: String) {
        dependencies {
            alias.forEach {
                add("implementation", libs.findBundle(it).get())
            }
        }
    }

    /**
     * Find the library by alias
     */
    fun Project.androidTestImplementation(vararg alias: String) {
        dependencies {
            alias.forEach {
                add("androidTestImplementation", libs.findLibrary(it).get())
            }
        }
    }

    /**
     * Find the library by alias
     */
    fun Project.androidTestImplementationBundle(vararg alias: String) {
        dependencies {
            alias.forEach {
                add("androidTestImplementation", libs.findBundle(it).get())
            }
        }
    }

    fun Project.androidTestImplementationPlatform(vararg alias: String) {
        dependencies {
            alias.forEach {
                add(
                    "androidTestImplementation",
                    dependencies.platform(libs.findLibrary(it).get())
                )
            }
        }
    }

    /**
     * Find the library by alias
     */
    fun Project.testImplementationBundle(vararg alias: String) {
        dependencies {
            alias.forEach {
                add("testImplementation", libs.findBundle(it).get())
            }
        }
    }

    /**
     * Find the library by alias
     */
    fun Project.coreLibraryDesugaring(vararg alias: String) {
        dependencies {
            alias.forEach {
                add("coreLibraryDesugaring", libs.findLibrary(it).get())
            }
        }
    }

    /**
     * Find the library by alias
     */
    fun Project.ksp(vararg alias: String) {
        dependencies {
            alias.forEach {
                add("ksp", libs.findLibrary(it).get())
            }
        }
    }
}
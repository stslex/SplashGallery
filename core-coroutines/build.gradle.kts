plugins {
    alias(libs.plugins.app.android.lib)
    kotlin("kapt")
}

dependencies {
    implementation(project(":core"))

    libs.apply {
        implementation(androidx.core)
        implementation(google.dagger.core)
        kapt(google.dagger.compiler)
        api(androidx.lifecycle.livedata)
        api(androidx.lifecycle.runtime)
        api(androidx.lifecycle.viewmodel)
    }
}
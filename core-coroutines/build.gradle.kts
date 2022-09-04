plugins {
    id("splashgallery.android.library")
    kotlin("kapt")
}

dependencies {
    implementation(project(":core"))
    implementation(project(":core-test"))

    libs.apply {
        implementation(androidx.core)
        implementation(google.dagger.core)
        kapt(google.dagger.compiler)
        api(androidx.lifecycle.livedata)
        api(androidx.lifecycle.runtime)
        api(androidx.lifecycle.viewmodel)
    }
}
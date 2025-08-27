plugins {
    alias(libs.plugins.app.android.lib)
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-parcelize")
    kotlin("kapt")
}

android {
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(project(":core"))
    implementation(project(":core-ui"))
    implementation(project(":core-coroutines"))
    implementation(project(":core-model"))
    implementation(project(":core-navigation"))

    libs.apply {
        implementation(androidx.paging.runtime)
        implementation(google.dagger.core)
        kapt(google.dagger.compiler)
        implementation(glide.core)
        kapt(glide.compiler)
        implementation(retrofit.core)
        implementation(retrofit.converter.gson)
        implementation(okhttp3.logging.interceptor)
        implementation(androidx.navigation.fragment)
        implementation(androidx.navigation.ui)
        implementation(androidx.core)
    }
}
plugins {
    alias(libs.plugins.app.android.lib)
    kotlin("kapt")
}

android {
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":core-coroutines"))

    libs.apply {
        implementation(google.dagger.core)
        kapt(google.dagger.compiler)

        implementation(glide.core)
        annotationProcessor(glide.compiler)

        api(androidx.appcompat)
        api(google.material)
        api(androidx.constraintlayout)

        implementation(androidx.navigation.fragment)
        implementation(androidx.navigation.ui)
        implementation(libs.androidx.paging.runtime)
    }
}
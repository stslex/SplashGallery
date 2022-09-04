plugins {
    id("splashgallery.android.library")
    kotlin("kapt")
}

android {
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":core-test"))
    implementation(project(":core-coroutines"))

    libs.apply {
        implementation(google.dagger.core)
        kapt(google.dagger.compiler)

        implementation(glide.core)
        annotationProcessor(glide.compiler)

        api(androidx.appcompat)
        api(google.material)
        api(androidx.constraintlayout)
    }
}
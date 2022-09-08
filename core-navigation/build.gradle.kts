plugins {
    id("splashgallery.android.library")
}

android {
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":core-test"))

    libs.apply {
        api(androidx.navigation.fragment)
        api(androidx.navigation.ui)
    }
}
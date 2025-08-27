plugins {
    alias(libs.plugins.app.android.lib)
}

android {
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":core"))

    libs.apply {
        api(androidx.navigation.fragment)
        api(androidx.navigation.ui)
    }
}
plugins {
    id("splashgallery.android.library")
}

dependencies {
    libs.apply {
        api(junit4)
        api(androidx.test.junit)
        api(androidx.test.espresso)
    }
}
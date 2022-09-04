plugins {
    id("splashgallery.android.library")
    id("kotlin-parcelize")
}

dependencies {
    implementation(project(":core"))
    implementation(project(":core-test"))
    implementation(libs.retrofit.converter.gson)
}
plugins {
    alias(libs.plugins.app.android.lib)
    id("kotlin-parcelize")
}

dependencies {
    implementation(project(":core"))
    implementation(libs.retrofit.converter.gson)
}
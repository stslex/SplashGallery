plugins {
    id("splashgallery.android.application")
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-parcelize")
    kotlin("kapt")
}

android {

    defaultConfig {
        applicationId = "com.stslex.splashgallery"
        targetSdk = 32
        versionCode = 9
        versionName = "1.09"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":core-test"))
    implementation(project(":core-ui"))
    implementation(project(":core-coroutines"))
    implementation(project(":feature-collections"))
    implementation(project(":core-model"))
    implementation(project(":core-navigation"))

    libs.apply {
        implementation(androidx.paging.runtime)
        implementation(google.dagger.core)
        kapt(google.dagger.compiler)
        implementation(glide.core)
        annotationProcessor(glide.compiler)
        implementation(retrofit.core)
        implementation(retrofit.converter.gson)
        implementation(okhttp3.logging.interceptor)
        implementation(androidx.navigation.fragment)
        implementation(androidx.navigation.ui)
        implementation(androidx.core)
    }
}
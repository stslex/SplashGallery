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

    libs.apply {
        implementation(androidx.paging.runtime)
        implementation(google.dagger.core)
        kapt(google.dagger.compiler)
        implementation(glide.core)
        annotationProcessor(glide.compiler)
        implementation(androidx.lifecycle.livedata)
        implementation(androidx.lifecycle.runtime)
        implementation(androidx.lifecycle.viewmodel)
        implementation(retrofit.core)
        implementation(retrofit.converter.gson)
        implementation(okhttp3.logging.interceptor)
        implementation(androidx.navigation.fragment)
        implementation(androidx.navigation.ui)
        implementation(androidx.core)
        implementation(androidx.appcompat)
        implementation(google.material)
        implementation(androidx.constraintlayout)
        testImplementation(junit4)
        androidTestImplementation(androidx.test.junit)
        androidTestImplementation(androidx.test.espresso)
    }
}
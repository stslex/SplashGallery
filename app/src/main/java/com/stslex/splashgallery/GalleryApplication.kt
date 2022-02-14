package com.stslex.splashgallery

import android.app.Application
import android.content.Context
import com.stslex.splashgallery.di.component.AppComponent
import com.stslex.splashgallery.di.component.DaggerAppComponent

class GalleryApplication : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().application(this).create()
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is GalleryApplication -> appComponent
        else -> (applicationContext as GalleryApplication).appComponent
    }

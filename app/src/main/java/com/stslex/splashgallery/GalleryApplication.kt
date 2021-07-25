package com.stslex.splashgallery

import android.app.Application
import android.content.Context
import com.stslex.splashgallery.di.AppComponent
import com.stslex.splashgallery.di.DaggerAppComponent

class GalleryApplication : Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is GalleryApplication -> appComponent
        else -> this.applicationContext.appComponent
    }

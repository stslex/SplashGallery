package com.stslex.splashgallery

import android.app.Application
import com.stslex.splashgallery.di.component.AppComponent
import com.stslex.splashgallery.di.component.DaggerAppComponent

class GalleryApplication : Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}

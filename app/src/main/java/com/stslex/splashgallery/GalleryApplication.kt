package com.stslex.splashgallery

import android.app.Application
import com.stslex.splashgallery.di.component.AppComponent
import com.stslex.splashgallery.di.component.DaggerAppComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class GalleryApplication : Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}

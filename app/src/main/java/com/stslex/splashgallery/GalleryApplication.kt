package com.stslex.splashgallery

import android.app.Application
import android.content.Context
import com.stslex.splashgallery.di.component.AppComponent
import com.stslex.splashgallery.di.component.DaggerAppComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class GalleryApplication : Application() {

    private var _appComponent: AppComponent? = null

    val appComponent: AppComponent
        get() = checkNotNull(_appComponent)

    override fun onCreate() {
        super.onCreate()
        _appComponent = DaggerAppComponent.create()
    }
}

@ExperimentalCoroutinesApi
val Context.appComponent: AppComponent
    get() = when (this) {
        is GalleryApplication -> appComponent
        else -> (applicationContext as GalleryApplication).appComponent
    }

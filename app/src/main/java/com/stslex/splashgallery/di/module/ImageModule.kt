package com.stslex.splashgallery.di.module

import android.app.Application
import coil.ImageLoader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ImageModule {

    @Singleton
    @Provides
    fun provideImageLoader(application: Application): ImageLoader {
        return ImageLoader(application)
    }
}
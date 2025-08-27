package com.stslex.splashgallery.feature_collections.di.module

import com.stslex.splashgallery.core_ui.ImageSetter
import dagger.Binds
import dagger.Module

@Module
interface UtilsModule {

    @Binds
    fun bindsImageSetter(setter: ImageSetter.Base): ImageSetter
}
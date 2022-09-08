package com.stslex.feature_collections.di.module

import com.stslex.core_ui.ImageSetter
import dagger.Binds
import dagger.Module

@Module
interface UtilsModule {

    @Binds
    fun bindsImageSetter(setter: ImageSetter.Base): ImageSetter
}
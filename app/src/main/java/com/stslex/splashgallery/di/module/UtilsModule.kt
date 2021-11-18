package com.stslex.splashgallery.di.module

import com.stslex.splashgallery.ui.detail_photo.DownloadImageUseCase
import com.stslex.splashgallery.utils.glide.ImageSetter
import dagger.Binds
import dagger.Module

@Module
interface UtilsModule {

    @Binds
    fun bindsDownloadImageUseCase(util: DownloadImageUseCase.Base): DownloadImageUseCase

    @Binds
    fun bindsImageSetter(setter: ImageSetter.Base): ImageSetter
}
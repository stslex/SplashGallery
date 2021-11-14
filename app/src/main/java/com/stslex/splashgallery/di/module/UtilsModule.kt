package com.stslex.splashgallery.di.module

import com.stslex.splashgallery.ui.detail_photo.DownloadImageUseCase
import dagger.Binds
import dagger.Module

@Module
interface UtilsModule {

    @Binds
    fun bindsDownloadImageUseCase(util: DownloadImageUseCase.Base): DownloadImageUseCase
}
package com.stslex.splashgallery.di.module

import com.stslex.splashgallery.data.utils.DataResponse
import com.stslex.splashgallery.data.utils.DataResponseImpl
import dagger.Binds
import dagger.Module

@Module
interface ResponseModule {

    @Binds
    fun bindsDataResponse(response: DataResponseImpl): DataResponse
}
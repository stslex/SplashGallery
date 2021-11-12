package com.stslex.splashgallery.di.module

import com.stslex.splashgallery.data.core.DataResponse
import dagger.Binds
import dagger.Module
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Module
interface ResponseModule {

    @Binds
    fun bindsDataResponse(response: DataResponse.Base): DataResponse
}
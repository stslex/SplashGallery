package com.stslex.splashgallery.di.module

import com.stslex.splashgallery.data.core.DataResponse
import com.stslex.splashgallery.ui.core.UIResponse
import dagger.Binds
import dagger.Module
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Module
interface ResponseModule {

    @Binds
    fun bindsDataResponse(response: DataResponse.Base): DataResponse

    @Binds
    fun bindsUIResponse(response: UIResponse.Base): UIResponse
}
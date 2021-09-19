package com.stslex.splashgallery.di.module

import com.stslex.splashgallery.data.core.DataResponse
import com.stslex.splashgallery.domain.core.DomainResponse
import dagger.Binds
import dagger.Module
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Module
interface TestResponseModule {

    @Binds
    fun bindsDataResponse(response: DataResponse.Base): DataResponse

    @Binds
    fun bindsDomainResponse(response: DomainResponse.Base): DomainResponse
}
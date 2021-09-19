package com.stslex.splashgallery.di.module

import com.stslex.splashgallery.data.core.DataResponse
import com.stslex.splashgallery.data.photos.PhotosData
import com.stslex.splashgallery.domain.PhotosDomain
import com.stslex.splashgallery.domain.core.DomainResponse
import com.stslex.splashgallery.domain.core.DomainResult
import dagger.Binds
import dagger.Module
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Module
interface TestResponseModule {

    @Binds
    fun bindsDataResponse(response: DataResponse.Base): DataResponse

    @Binds
    fun bindsDomainPhotosResponse(response: DomainResponse.Base<List<PhotosData>, DomainResult<List<PhotosDomain>>>):
            DomainResponse<List<PhotosData>, DomainResult<List<PhotosDomain>>>
}
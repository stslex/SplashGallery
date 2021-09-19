package com.stslex.splashgallery.di.module

import com.stslex.splashgallery.core.Abstract
import com.stslex.splashgallery.data.photos.PhotosData
import com.stslex.splashgallery.data.photos.PhotosDataMapper
import com.stslex.splashgallery.domain.PhotosDomain
import com.stslex.splashgallery.domain.PhotosDomainMapper
import com.stslex.splashgallery.domain.core.DomainResult
import com.stslex.splashgallery.ui.core.UIResult
import com.stslex.splashgallery.ui.photos.PhotosUI
import dagger.Module
import dagger.Provides

@Module
class MapperModule {

    @Provides
    fun providesPhotosDataMapper(): Abstract.Mapper.DataToDomain<List<PhotosData>, DomainResult<List<PhotosDomain>>> =
        PhotosDataMapper.Base()

    @Provides
    fun providesPhotosDomainMapper(): Abstract.Mapper.DomainToUi<List<PhotosDomain>, UIResult<List<PhotosUI>>> =
        PhotosDomainMapper.Base()

}
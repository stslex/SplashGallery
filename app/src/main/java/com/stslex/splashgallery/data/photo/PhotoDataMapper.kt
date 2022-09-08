package com.stslex.splashgallery.data.photo

import com.stslex.core.Mapper
import com.stslex.core.Resource
import com.stslex.core_model.data.image.ImageDataModel
import com.stslex.splashgallery.data.core.map
import st.slex.csplashscreen.data.model.remote.image.RemoteImageModel
import javax.inject.Inject


interface PhotoDataMapper : Mapper.DataToUI<RemoteImageModel, Resource<ImageDataModel>> {

    class Base @Inject constructor() : PhotoDataMapper {

        override fun map(data: RemoteImageModel): Resource<ImageDataModel> =
            Resource.Success(data.map())

        override fun map(exception: Exception): Resource<ImageDataModel> =
            Resource.Failure(exception)

        override fun map(): Resource<ImageDataModel> = Resource.Loading
    }
}
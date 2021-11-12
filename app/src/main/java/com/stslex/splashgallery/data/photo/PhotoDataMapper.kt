package com.stslex.splashgallery.data.photo

import com.stslex.splashgallery.core.Mapper
import com.stslex.splashgallery.core.Resource
import com.stslex.splashgallery.data.core.toImageModel
import com.stslex.splashgallery.data.model.image.RemoteImageModel
import com.stslex.splashgallery.ui.model.image.ImageModel
import javax.inject.Inject


interface PhotoDataMapper : Mapper.DataToUI<RemoteImageModel, Resource<ImageModel>> {

    class Base @Inject constructor() : PhotoDataMapper {

        override fun map(data: RemoteImageModel): Resource<ImageModel> =
            Resource.Success(data.toImageModel())

        override fun map(exception: Exception): Resource<ImageModel> =
            Resource.Failure(exception)

        override fun map(): Resource<ImageModel> = Resource.Loading
    }
}
package com.stslex.splashgallery.data.photo

import com.stslex.splashgallery.core.Abstract
import com.stslex.splashgallery.data.model.image.RemoteImageModel
import com.stslex.splashgallery.data.toImageModel
import com.stslex.splashgallery.ui.detail_photo.UIResult
import com.stslex.splashgallery.ui.model.image.ImageModel
import javax.inject.Inject


interface PhotoDataMapper<T> : Abstract.Mapper.DataToDomain<RemoteImageModel, T> {

    class Base @Inject constructor() : PhotoDataMapper<UIResult<ImageModel>>,
        Abstract.Mapper.DataToUI<RemoteImageModel, UIResult<ImageModel>> {
        override fun map(data: RemoteImageModel): UIResult<ImageModel> =
            UIResult.Success(data.toImageModel())

        override fun map(exception: Exception): UIResult<ImageModel> =
            UIResult.Failure(exception)

    }
}
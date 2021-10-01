package com.stslex.splashgallery.data.photo

import com.stslex.splashgallery.core.Abstract
import com.stslex.splashgallery.data.model.image.RemoteImageModel
import com.stslex.splashgallery.data.toImageModel
import com.stslex.splashgallery.ui.core.UIResult
import com.stslex.splashgallery.ui.model.image.ImageModel
import javax.inject.Inject


class PhotoDataMapper @Inject constructor() :
    Abstract.Mapper.DataToUI<RemoteImageModel, UIResult<ImageModel>> {

    override fun map(data: RemoteImageModel): UIResult<ImageModel> =
        UIResult.Success(data.toImageModel())

    override fun map(exception: Exception): UIResult<ImageModel> =
        UIResult.Failure(exception)
}
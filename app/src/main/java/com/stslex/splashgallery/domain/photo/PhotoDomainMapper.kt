package com.stslex.splashgallery.domain.photo

import com.stslex.splashgallery.core.Abstract
import com.stslex.splashgallery.ui.detail_photo.PhotoUIResult
import com.stslex.splashgallery.ui.photos.PhotosUI
import javax.inject.Inject

interface PhotoDomainMapper<T> : Abstract.Mapper.DomainToUi<PhotoDomain, T> {

    class Base @Inject constructor() : PhotoDomainMapper<PhotoUIResult> {
        override fun map(data: PhotoDomain): PhotoUIResult = with(data) {
            PhotoUIResult.Success(
                PhotosUI.Base(
                    imageId = imageId(),
                    imageUrl = imageUrl(),
                    userId = userId(),
                    userName = userName(),
                    userUrl = userUrl()
                )
            )
        }

        override fun map(exception: String): PhotoUIResult =
            PhotoUIResult.Failure(exception)
    }
}
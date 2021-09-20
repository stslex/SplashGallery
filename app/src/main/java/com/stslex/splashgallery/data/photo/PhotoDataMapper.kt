package com.stslex.splashgallery.data.photo

import com.stslex.splashgallery.core.Abstract
import com.stslex.splashgallery.data.photos.PhotoData
import com.stslex.splashgallery.domain.photo.PhotoDomain
import com.stslex.splashgallery.domain.photo.PhotoDomainResult
import javax.inject.Inject


interface PhotoDataMapper<T> : Abstract.Mapper.DataToDomain<PhotoData, T> {

    class Base @Inject constructor() : PhotoDataMapper<PhotoDomainResult> {
        override fun map(data: PhotoData): PhotoDomainResult =
            PhotoDomainResult.Success(
                with(data) {
                    PhotoDomain.Base(
                        imageId = imageId(),
                        imageUrl = imageUrl(),
                        userId = userId(),
                        userName = userName(),
                        userUrl = userUrl()
                    )
                }
            )

        override fun map(exception: String): PhotoDomainResult =
            PhotoDomainResult.Failure(exception)

    }
}
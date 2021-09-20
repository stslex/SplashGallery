package com.stslex.splashgallery.data.photos

import com.stslex.splashgallery.core.Abstract
import com.stslex.splashgallery.domain.photo.PhotoDomain
import com.stslex.splashgallery.domain.photos.PhotosDomainResult
import javax.inject.Inject

interface PhotosDataMapper<T> : Abstract.Mapper.DataToDomain<List<PhotoData>, T> {

    class Base @Inject constructor() : PhotosDataMapper<PhotosDomainResult> {
        override fun map(data: List<PhotoData>): PhotosDomainResult =
            PhotosDomainResult.Success(data.map {
                PhotoDomain.Base(
                    imageId = it.imageId(),
                    imageUrl = it.imageUrl(),
                    userId = it.userId(),
                    userName = it.userName(),
                    userUrl = it.userUrl()
                )
            })

        override fun map(exception: String): PhotosDomainResult =
            PhotosDomainResult.Failure(exception)

    }
}
package com.stslex.splashgallery.data.photos

import com.stslex.splashgallery.core.Abstract
import com.stslex.splashgallery.domain.PhotosDomain
import com.stslex.splashgallery.domain.PhotosDomainResult
import javax.inject.Inject

interface PhotosDataMapper<T> : Abstract.Mapper.DataToDomain<List<PhotosData>, T> {

    class Base @Inject constructor() : PhotosDataMapper<PhotosDomainResult> {
        override fun map(data: List<PhotosData>): PhotosDomainResult =
            PhotosDomainResult.Success(data.map {
                PhotosDomain.Base(
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
package com.stslex.splashgallery.data.photos

import com.stslex.splashgallery.core.Abstract
import com.stslex.splashgallery.domain.PhotosDomain
import com.stslex.splashgallery.domain.core.DomainResult

interface PhotosDataMapper<T> : Abstract.Mapper.DataToDomain<List<PhotosData>, T> {

    class Base : PhotosDataMapper<DomainResult<List<PhotosDomain>>> {
        override fun map(data: List<PhotosData>): DomainResult<List<PhotosDomain>> =
            DomainResult.Success(data.map {
                PhotosDomain.Base(
                    imageId = it.imageId(),
                    imageUrl = it.imageUrl(),
                    userId = it.userId(),
                    userName = it.userName(),
                    userUrl = it.userUrl()
                )
            })

        override fun map(exception: String): DomainResult<List<PhotosDomain>> =
            DomainResult.Failure(exception)

    }
}
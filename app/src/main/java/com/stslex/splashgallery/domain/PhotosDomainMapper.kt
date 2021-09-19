package com.stslex.splashgallery.domain

import com.stslex.splashgallery.core.Abstract
import com.stslex.splashgallery.ui.core.UIResult
import com.stslex.splashgallery.ui.photos.PhotosUI

interface PhotosDomainMapper<T> : Abstract.Mapper.DomainToUi<List<PhotosDomain>, T> {

    class Base : PhotosDomainMapper<UIResult<List<PhotosUI>>> {
        override fun map(data: List<PhotosDomain>): UIResult<List<PhotosUI>> =
            UIResult.Success(data.map {
                PhotosUI.Base(
                    imageId = it.imageId(),
                    imageUrl = it.imageUrl(),
                    userId = it.userId(),
                    userName = it.userName(),
                    userUrl = it.userUrl()
                )
            })

        override fun map(exception: String): UIResult<List<PhotosUI>> =
            UIResult.Failure(exception)
    }
}
package com.stslex.splashgallery.data.collections

import com.stslex.splashgallery.core.Abstract
import com.stslex.splashgallery.domain.collections.CollectionDomain
import com.stslex.splashgallery.domain.collections.CollectionDomainResult
import javax.inject.Inject

interface CollectionDataMapper<T> : Abstract.Mapper.DataToDomain<List<CollectionData>, T> {

    class Base @Inject constructor() : CollectionDataMapper<CollectionDomainResult> {
        override fun map(data: List<CollectionData>): CollectionDomainResult =
            CollectionDomainResult.Success(
                data.map {
                    CollectionDomain.Base(
                        id = it.id(),
                        title = it.title(),
                        url = it.url(),
                        number = it.number(),
                        username = it.username(),
                        avatar = it.avatar()
                    )
                }
            )

        override fun map(exception: String): CollectionDomainResult =
            CollectionDomainResult.Failure(exception)

    }
}
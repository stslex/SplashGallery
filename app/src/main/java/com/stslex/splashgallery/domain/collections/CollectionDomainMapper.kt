package com.stslex.splashgallery.domain.collections

import com.stslex.splashgallery.core.Abstract
import com.stslex.splashgallery.ui.collections.CollectionUI
import com.stslex.splashgallery.ui.collections.CollectionUIResult
import javax.inject.Inject

interface CollectionDomainMapper<T> : Abstract.Mapper.DomainToUi<List<CollectionDomain>, T> {

    class Base @Inject constructor() : CollectionDomainMapper<CollectionUIResult> {
        override fun map(data: List<CollectionDomain>): CollectionUIResult =
            CollectionUIResult.Success(data.map {
                CollectionUI.Base(
                    id = it.id(),
                    title = it.title(),
                    url = it.url(),
                    number = it.number(),
                    username = it.username(),
                    avatar = it.avatar()
                )
            })

        override fun map(exception: String): CollectionUIResult =
            CollectionUIResult.Failure(exception)

    }
}
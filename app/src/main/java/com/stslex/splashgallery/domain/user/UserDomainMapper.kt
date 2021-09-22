package com.stslex.splashgallery.domain.user

import com.stslex.splashgallery.core.Abstract
import com.stslex.splashgallery.ui.user.UserUI
import com.stslex.splashgallery.ui.user.UserUIResult
import javax.inject.Inject

interface UserDomainMapper<T> : Abstract.Mapper.DomainToUi<UserDomain, T> {

    class Base @Inject constructor() : UserDomainMapper<UserUIResult> {

        override fun map(data: UserDomain): UserUIResult = UserUIResult.Success(
            with(data) {
                UserUI.Base(
                    username = username(),
                    url = url(),
                    totalCollections = totalCollections(),
                    totalPhotos = totalPhotos(),
                    totalLikes = totalLikes(),
                    bio = bio()
                )
            }
        )

        override fun map(exception: String): UserUIResult = UserUIResult.Failure(exception)
    }
}
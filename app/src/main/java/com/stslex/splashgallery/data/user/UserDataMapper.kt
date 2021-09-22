package com.stslex.splashgallery.data.user

import com.stslex.splashgallery.core.Abstract
import com.stslex.splashgallery.domain.user.UserDomain
import com.stslex.splashgallery.domain.user.UserDomainResult
import javax.inject.Inject

interface UserDataMapper<T> : Abstract.Mapper.DataToDomain<UserData, T> {

    class Base @Inject constructor() : UserDataMapper<UserDomainResult> {

        override fun map(data: UserData): UserDomainResult = UserDomainResult.Success(
            with(data) {
                UserDomain.Base(
                    username = username(),
                    url = url(),
                    totalCollections = totalCollections(),
                    totalPhotos = totalPhotos(),
                    totalLikes = totalLikes(),
                    bio = bio()
                )
            }
        )

        override fun map(exception: String): UserDomainResult = UserDomainResult.Failure(exception)
    }
}
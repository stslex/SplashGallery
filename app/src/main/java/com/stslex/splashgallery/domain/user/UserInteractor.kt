package com.stslex.splashgallery.domain.user

import com.stslex.splashgallery.data.user.UserRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalCoroutinesApi
interface UserInteractor {

    suspend fun getUser(username: String): Flow<UserDomainResult>

    class Base @Inject constructor(
        private val repository: UserRepository,
        private val response: UserDomainResponse
    ) : UserInteractor {

        override suspend fun getUser(username: String): Flow<UserDomainResult> =
            response.create(repository.getUser(username))
    }
}
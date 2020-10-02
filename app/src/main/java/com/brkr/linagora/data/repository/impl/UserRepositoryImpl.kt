package com.brkr.linagora.data.repository.impl

import com.brkr.linagora.data.repository.remote.RestApi
import com.brkr.linagora.domain.model.User
import com.brkr.linagora.domain.repository.UserRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

class UserRepositoryImpl : UserRepository, KoinComponent {

    private val restApi: RestApi by inject()

    override suspend fun findUserByUsername(username: String): User? {
        val result = restApi.getUserByUsername(username)
        return result.user?.toUser()
    }
}

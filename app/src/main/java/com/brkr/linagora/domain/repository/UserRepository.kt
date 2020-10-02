package com.brkr.linagora.domain.repository

import com.brkr.linagora.domain.model.User

interface UserRepository {

    suspend fun findUserByUsername(username: String): User?
}

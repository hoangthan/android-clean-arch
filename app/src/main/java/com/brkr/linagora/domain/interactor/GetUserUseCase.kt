package com.brkr.linagora.domain.interactor

import com.brkr.linagora.domain.model.User

interface GetUserUseCase {

    suspend fun execute(param: GetUserParam): User?

    data class GetUserParam(
        val username: String
    )
}

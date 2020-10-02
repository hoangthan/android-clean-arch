package com.brkr.linagora.domain.interactor.impl

import com.brkr.linagora.domain.interactor.GetUserUseCase
import com.brkr.linagora.domain.interactor.GetUserUseCase.GetUserParam
import com.brkr.linagora.domain.model.User
import com.brkr.linagora.domain.repository.UserRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

class GetUserUseCaseImpl : GetUserUseCase, KoinComponent {

    private val userRepository: UserRepository by inject()

    override suspend fun execute(param: GetUserParam): User? {
        return userRepository.findUserByUsername(param.username)
    }
}

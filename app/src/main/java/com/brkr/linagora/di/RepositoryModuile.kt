package com.brkr.linagora.di

import com.brkr.linagora.data.repository.impl.UserRepositoryImpl
import com.brkr.linagora.data.repository.remote.RestApi
import com.brkr.linagora.data.repository.remote.config.ApiFactory
import com.brkr.linagora.domain.repository.UserRepository
import org.koin.dsl.module

val RepositoryModule = module {
    single { createRestApi() }
    single { createUserRepository() }
}

fun createRestApi(): RestApi {
    return ApiFactory().instance
}

fun createUserRepository(): UserRepository {
    return UserRepositoryImpl()
}

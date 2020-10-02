package com.brkr.linagora.di

import com.brkr.linagora.data.repository.impl.ProductRepositoryImpl
import com.brkr.linagora.data.repository.impl.PurchaseRepositoryImpl
import com.brkr.linagora.data.repository.impl.UserRepositoryImpl
import com.brkr.linagora.data.repository.remote.RestApi
import com.brkr.linagora.data.repository.remote.config.ApiFactory
import com.brkr.linagora.domain.repository.ProductRepository
import com.brkr.linagora.domain.repository.PurchaseRepository
import com.brkr.linagora.domain.repository.UserRepository
import org.koin.dsl.module

val RepositoryModule = module {
    single { createRestApi() }
    single { createUserRepository() }
    single { createPurchaseRepository() }
    single { createProductRepository() }
}

fun createRestApi(): RestApi {
    return ApiFactory().instance
}

fun createUserRepository(): UserRepository {
    return UserRepositoryImpl()
}

fun createPurchaseRepository(): PurchaseRepository {
    return PurchaseRepositoryImpl()
}

fun createProductRepository() : ProductRepository {
    return ProductRepositoryImpl()
}

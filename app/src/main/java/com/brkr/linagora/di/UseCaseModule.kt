package com.brkr.linagora.di

import com.brkr.linagora.domain.interactor.GetProductUseCase
import com.brkr.linagora.domain.interactor.GetPurchasedProductUseCase
import com.brkr.linagora.domain.interactor.GetUserUseCase
import com.brkr.linagora.domain.interactor.impl.GetProductUseCaseImpl
import com.brkr.linagora.domain.interactor.impl.GetPurchasedProductUseCaseImpl
import com.brkr.linagora.domain.interactor.impl.GetUserUseCaseImpl
import org.koin.dsl.module

val UseCaseModule = module {
    single { createGetUserUseCase() }
    single { createGetProductUseCase() }
    single { createGetPurchasedProductUseCase() }
}

fun createGetUserUseCase(): GetUserUseCase {
    val getUserUseCase: GetUserUseCase = GetUserUseCaseImpl()
    return getUserUseCase
}

fun createGetProductUseCase(): GetProductUseCase {
    val getProductUseCase: GetProductUseCase = GetProductUseCaseImpl()
    return getProductUseCase
}

fun createGetPurchasedProductUseCase(): GetPurchasedProductUseCase {
    val purchasedProductUseCase: GetPurchasedProductUseCase = GetPurchasedProductUseCaseImpl()
    return purchasedProductUseCase
}

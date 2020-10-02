package com.brkr.linagora.di

import com.brkr.linagora.domain.interactor.GetProductUseCase
import com.brkr.linagora.domain.interactor.GetPurchaseByProductUseCase
import com.brkr.linagora.domain.interactor.GetPurchaseByUserUseCase
import com.brkr.linagora.domain.interactor.GetUserUseCase
import com.brkr.linagora.domain.interactor.impl.GetProductUseCaseImpl
import com.brkr.linagora.domain.interactor.impl.GetPurchaseByProductUseCaseImpl
import com.brkr.linagora.domain.interactor.impl.GetPurchaseByUserUseCaseImpl
import com.brkr.linagora.domain.interactor.impl.GetUserUseCaseImpl
import org.koin.dsl.module

val UseCaseModule = module {
    single { createGetUserUseCase() }
    single { createGetProductUseCase() }
    single { createGetPurchasedUseCase() }
    single { createGetPurchaseByProductUseCase() }
}

fun createGetUserUseCase(): GetUserUseCase {
    val getUserUseCase: GetUserUseCase = GetUserUseCaseImpl()
    return getUserUseCase
}

fun createGetProductUseCase(): GetProductUseCase {
    val getProductUseCase: GetProductUseCase = GetProductUseCaseImpl()
    return getProductUseCase
}

fun createGetPurchasedUseCase(): GetPurchaseByUserUseCase {
    val purchaseByUserUseCase: GetPurchaseByUserUseCase = GetPurchaseByUserUseCaseImpl()
    return purchaseByUserUseCase
}

fun createGetPurchaseByProductUseCase(): GetPurchaseByProductUseCase {
    val getPurchaseByProductUseCase = GetPurchaseByProductUseCaseImpl()
    return getPurchaseByProductUseCase
}

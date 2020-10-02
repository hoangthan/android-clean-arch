package com.brkr.linagora.domain.interactor.impl

import com.brkr.linagora.domain.interactor.GetPurchasedProductUseCase
import com.brkr.linagora.domain.interactor.GetPurchasedProductUseCase.GetPurchaseProductParam
import com.brkr.linagora.domain.model.PurchasedProduct
import com.brkr.linagora.domain.repository.PurchasedProductRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

class GetPurchasedProductUseCaseImpl : GetPurchasedProductUseCase, KoinComponent {

    private val purchasedProductRepository: PurchasedProductRepository by inject()

    override suspend fun execute(param: GetPurchaseProductParam): List<PurchasedProduct> {
        return purchasedProductRepository.getListPurchasedBy(param.username, param.limit)
    }
}

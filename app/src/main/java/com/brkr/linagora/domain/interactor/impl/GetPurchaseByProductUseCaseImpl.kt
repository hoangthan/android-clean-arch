package com.brkr.linagora.domain.interactor.impl

import com.brkr.linagora.domain.interactor.GetPurchaseByProductUseCase
import com.brkr.linagora.domain.interactor.GetPurchaseByProductUseCase.PurchaseProductParam
import com.brkr.linagora.domain.model.Purchase
import com.brkr.linagora.domain.repository.PurchaseRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

class GetPurchaseByProductUseCaseImpl : GetPurchaseByProductUseCase, KoinComponent {

    private val purchaseRepository: PurchaseRepository by inject()

    override suspend fun execute(param: PurchaseProductParam): List<Purchase> {
        return purchaseRepository.getListPurchaseByProductId(param.productId)
    }
}

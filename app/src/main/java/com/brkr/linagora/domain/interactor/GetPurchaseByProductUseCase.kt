package com.brkr.linagora.domain.interactor

import com.brkr.linagora.domain.model.Purchase

interface GetPurchaseByProductUseCase {

    suspend fun execute(param: PurchaseProductParam): List<Purchase>

    data class PurchaseProductParam(
        val productId: Int
    )
}

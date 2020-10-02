package com.brkr.linagora.domain.interactor

import com.brkr.linagora.domain.model.PurchasedProduct

interface GetPurchasedProductUseCase {

    suspend fun execute(param: GetPurchaseProductParam): List<PurchasedProduct>

    data class GetPurchaseProductParam(
        val username: String,
        val limit: Int
    )
}

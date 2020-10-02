package com.brkr.linagora.domain.interactor

import com.brkr.linagora.domain.model.Purchase

interface GetPurchaseByUserUseCase {

    suspend fun execute(param: GetPurchaseParam): List<Purchase>

    data class GetPurchaseParam(
        val username: String,
        val limit: Int
    )
}

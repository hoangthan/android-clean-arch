package com.brkr.linagora.data.repository.impl

import com.brkr.linagora.data.repository.remote.RestApi
import com.brkr.linagora.domain.model.Purchase
import com.brkr.linagora.domain.repository.PurchaseRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

class PurchaseRepositoryImpl : PurchaseRepository, KoinComponent {

    private val restApi: RestApi by inject()

    override suspend fun getListPurchaseByUsername(username: String, limit: Int): List<Purchase> {
        val result = restApi.getPurchaseByUser(username, limit)
        return result.purchases.map { it.toPurchase() }
    }

    override suspend fun getListPurchaseByProductId(productId: Int): List<Purchase> {
        val result = restApi.getPurchaseByProductId(productId)
        return result.purchases.map { it.toPurchase() }
    }
}

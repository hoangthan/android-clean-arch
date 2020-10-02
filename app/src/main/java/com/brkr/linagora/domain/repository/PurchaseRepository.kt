package com.brkr.linagora.domain.repository

import com.brkr.linagora.domain.model.Purchase

interface PurchaseRepository {

    suspend fun getListPurchaseByUsername(username: String, limit: Int): List<Purchase>

    suspend fun getListPurchaseByProductId(productId: Int): List<Purchase>
}

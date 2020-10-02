package com.brkr.linagora.domain.repository

import com.brkr.linagora.domain.model.PurchasedProduct

interface PurchasedProductRepository {

    fun getListPurchasedBy(username: String, limit: Int): List<PurchasedProduct>
}

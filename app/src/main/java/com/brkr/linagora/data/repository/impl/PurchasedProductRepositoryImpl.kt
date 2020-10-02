package com.brkr.linagora.data.repository.impl

import com.brkr.linagora.data.repository.remote.RestApi
import com.brkr.linagora.domain.model.PurchasedProduct
import com.brkr.linagora.domain.repository.PurchasedProductRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

class PurchasedProductRepositoryImpl: PurchasedProductRepository, KoinComponent {

    private val restApi: RestApi by inject()

    override fun getListPurchasedBy(username: String, limit: Int): List<PurchasedProduct> {
        return listOf()
    }
}

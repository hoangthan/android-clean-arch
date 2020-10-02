package com.brkr.linagora.data.repository.impl

import com.brkr.linagora.data.repository.remote.RestApi
import com.brkr.linagora.domain.model.Product
import com.brkr.linagora.domain.repository.ProductRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

class ProductRepositoryImpl : ProductRepository, KoinComponent {

    private val restApi: RestApi by inject()

    override fun findProductById(id: Int): List<Product> {
        return listOf()
    }
}

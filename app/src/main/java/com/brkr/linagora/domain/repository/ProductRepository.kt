package com.brkr.linagora.domain.repository

import com.brkr.linagora.domain.model.Product

interface ProductRepository {

    suspend fun findProductById(id: Int): Product
}

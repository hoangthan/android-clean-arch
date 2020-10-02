package com.brkr.linagora.domain.repository

import com.brkr.linagora.domain.model.Product

interface ProductRepository {

    fun findProductById(id: Int): List<Product>
}

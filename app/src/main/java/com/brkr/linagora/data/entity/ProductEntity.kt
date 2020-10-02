package com.brkr.linagora.data.entity

import com.brkr.linagora.domain.model.Product
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductEntity(
    val face: String,
    val id: Int,
    val price: Int,
    val size: Int
) {

    fun toProduct(): Product {
        return Product(this.face, this.id, this.price, this.size)
    }
}

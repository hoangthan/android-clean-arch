package com.brkr.linagora.data.entity

import com.brkr.linagora.domain.model.Purchase
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PurchaseEntity(
    val date: String,
    val id: Int,
    val productId: Int,
    val username: String
) {

    fun toPurchase(): Purchase {
        return Purchase(
            date = this.date,
            id = this.id,
            productId = this.productId,
            username = this.username
        )
    }
}

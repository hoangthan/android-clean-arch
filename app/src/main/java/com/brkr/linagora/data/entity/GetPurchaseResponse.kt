package com.brkr.linagora.data.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetPurchaseResponse(
    val purchases: List<PurchaseEntity>
)

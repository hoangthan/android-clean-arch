package com.brkr.linagora.domain.model

data class Purchase(
    val date: String,
    val id: Int,
    val productId: Int,
    val username: String
)

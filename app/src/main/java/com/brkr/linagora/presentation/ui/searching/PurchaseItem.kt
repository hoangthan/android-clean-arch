package com.brkr.linagora.presentation.ui.searching

import com.brkr.linagora.domain.model.Product
import com.brkr.linagora.domain.model.Purchase

data class PurchaseItem(
    val purchase: Purchase,
    var product: Product,
    var users: List<String>
) {

    fun getBuyerName(): String {
        return users.joinToString(" ,")
    }
}

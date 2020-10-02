package com.brkr.linagora.presentation.ui.searching

import com.brkr.linagora.domain.model.Product
import com.brkr.linagora.domain.model.Purchase
import com.brkr.linagora.domain.model.User

data class PurchaseItem(
    val purchase: Purchase,
    var details: Product,
    var users: List<String>
)

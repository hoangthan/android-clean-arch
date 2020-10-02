package com.brkr.linagora.presentation.ui.searching

import android.view.View
import android.view.inputmethod.EditorInfo
import com.brkr.linagora.R
import com.brkr.linagora.domain.model.Purchase
import com.brkr.linagora.presentation.ui.base.BaseFragment
import com.brkr.linagora.presentation.utils.ui
import com.brkr.linagora.presentation.utils.value
import kotlinx.android.synthetic.main.fragment_searching.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchingFragment : BaseFragment() {

    override var layoutId: Int = R.layout.fragment_searching

    private val viewModel: SearchingViewModel by viewModel()

    private val purchaseItems = mutableListOf<PurchaseItem>()

    override fun initViews() {
        //Pass
    }

    override fun initDataObserver() {
        //Pass
    }

    override fun initViewListeners() {
        edtUsername.setOnEditorActionListener { _, actionId, _ ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    searchByUsername()
                    true
                }
                else -> false
            }
        }
    }

    private fun searchByUsername() = ui {
        val username = edtUsername.value()
        showLoading()

        //Check if username is exist.
        val result = viewModel.getUserByUsernameAsync(username).await()
        result ?: run {
            showToast(R.string.user_not_found)
            return@ui
        }

        //Fetch 5 recent purchased
        val recentPurchase = viewModel.getRecentPurchaseAsync(username).await()
        getProductDetails(recentPurchase)
    }

    private suspend fun getProductDetails(recentPurchase: List<Purchase>) {
        recentPurchase.forEach { purchase ->
            //Get buyer
            val purchaseByProduct = viewModel.getPurchaseByProduct(purchase.productId).await()
            //Get details
            val productDetails = viewModel.getProductDetails(purchase.productId).await()
            //Add to list item
            val usernames = purchaseByProduct.map { it.username }
            purchaseItems.add(PurchaseItem(purchase, productDetails, usernames))
        }
        purchaseItems.sortByDescending { it.details.price }
    }

    override fun onClick(p0: View?) {
        //Pass
    }
}

package com.brkr.linagora.presentation.ui.searching

import android.view.View
import android.view.inputmethod.EditorInfo
import com.brkr.linagora.R
import com.brkr.linagora.presentation.ui.base.BaseFragment
import com.brkr.linagora.presentation.utils.ui
import com.brkr.linagora.presentation.utils.value
import kotlinx.android.synthetic.main.fragment_searching.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchingFragment : BaseFragment() {

    override var layoutId: Int = R.layout.fragment_searching

    private val viewModel: SearchingViewModel by viewModel()

    override fun initViews() {
        //Pass
    }

    override fun initDataObserver() {
        viewModel.purchaseItemsLiveData.observe(viewLifecycleOwner, {
            updatePurchaseView(it)
        })
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
            hideLoading()
            showToast(R.string.user_not_found)
            return@ui
        }

        //Fetch recent purchases
        val recentPurchase = viewModel.getRecentPurchaseAsync(username).await()
        if (recentPurchase.isEmpty()) {
            showToast(R.string.purchase_not_found)
        } else {
            viewModel.loadPurchaseItemDetails(recentPurchase).await()
        }
        hideLoading()
    }

    private fun updatePurchaseView(purchaseItems: List<PurchaseItem>) {
        val purchaseAdapter = PurchaseAdapter(purchaseItems) {
            showToast("" + it)
        }
        rcvPurchase.apply {
            this.setHasFixedSize(true)
            this.adapter = purchaseAdapter
        }
    }

    override fun onClick(p0: View?) {
        //Pass
    }
}

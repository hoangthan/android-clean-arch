package com.brkr.linagora.presentation.ui.searching

import android.view.View
import android.view.inputmethod.EditorInfo
import com.brkr.linagora.R
import com.brkr.linagora.presentation.ui.base.BaseFragment
import com.brkr.linagora.presentation.ui.product.ProductDetailsFragment
import com.brkr.linagora.presentation.ui.product.ProductDetailsFragment.Companion.TAG
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
            viewModel.loadPurchaseItemDetailsAsync(recentPurchase).await()
        }
        hideLoading()
    }

    private fun updatePurchaseView(purchaseItems: List<PurchaseItem>) {
        //Create purchase adapter and set callback when item is clicked
        val purchaseAdapter = PurchaseAdapter(purchaseItems) { handleOnItemClicked(it) }

        //Set adapter for recycler view.
        rcvPurchase.apply {
            this.setHasFixedSize(true)
            this.adapter = purchaseAdapter
        }
    }

    private fun handleOnItemClicked(position: Int) {
        val product = viewModel.purchaseItemsLiveData.value?.get(position)
        product ?: return
        requireActivity().supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .add(R.id.fragmentContainerView, ProductDetailsFragment(product), TAG)
            .commit()
    }

    override fun onClick(p0: View?) {
        //Pass
    }
}

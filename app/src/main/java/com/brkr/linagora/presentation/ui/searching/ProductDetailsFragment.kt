package com.brkr.linagora.presentation.ui.searching

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.brkr.linagora.R
import com.brkr.linagora.databinding.FragmentProductDetailsBinding
import com.brkr.linagora.presentation.ui.base.BaseFragment

class ProductDetailsFragment(
    private val purchaseItem: PurchaseItem
) : BaseFragment() {

    override var layoutId: Int = R.layout.fragment_product_details

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentProductDetailsBinding = FragmentProductDetailsBinding.inflate(inflater)
        fragmentProductDetailsBinding.product = purchaseItem.product
        return fragmentProductDetailsBinding.root
    }

    override fun initViews() {
        //Pass
    }

    override fun initDataObserver() {
        //Pass
    }

    override fun initViewListeners() {
        //Pass
    }

    override fun onClick(p0: View?) {
        //Pass
    }

    companion object {
        const val TAG = "ProductDetailsFragment"
    }
}

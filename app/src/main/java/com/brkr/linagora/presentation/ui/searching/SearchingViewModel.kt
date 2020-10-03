package com.brkr.linagora.presentation.ui.searching

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brkr.linagora.domain.interactor.GetProductUseCase
import com.brkr.linagora.domain.interactor.GetProductUseCase.GetProductParam
import com.brkr.linagora.domain.interactor.GetPurchaseByProductUseCase
import com.brkr.linagora.domain.interactor.GetPurchaseByProductUseCase.PurchaseProductParam
import com.brkr.linagora.domain.interactor.GetPurchaseByUserUseCase
import com.brkr.linagora.domain.interactor.GetPurchaseByUserUseCase.GetPurchaseParam
import com.brkr.linagora.domain.interactor.GetUserUseCase
import com.brkr.linagora.domain.interactor.GetUserUseCase.GetUserParam
import com.brkr.linagora.domain.model.Purchase
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent
import org.koin.core.inject

class SearchingViewModel : ViewModel(), KoinComponent {

    private val getUserUseCase: GetUserUseCase by inject()
    private val getPurchaseByUserUseCase: GetPurchaseByUserUseCase by inject()
    private val getPurchaseByProductUseCase: GetPurchaseByProductUseCase by inject()
    private val getProductUseCase: GetProductUseCase by inject()

    val purchaseItemsLiveData = MutableLiveData<List<PurchaseItem>>()

    fun getUserByUsernameAsync(username: String) = viewModelScope.async(IO) {
        viewModelScope.ensureActive()
        val getUserParam = GetUserParam(username)
        getUserUseCase.execute(getUserParam)
    }

    fun getRecentPurchaseAsync(username: String) = viewModelScope.async(IO) {
        viewModelScope.ensureActive()
        val param = GetPurchaseParam(username, PURCHASE_FETCH_LIMIT)
        getPurchaseByUserUseCase.execute(param)
    }

    private suspend fun getProductDetails(productId: Int) =
        withContext(viewModelScope.coroutineContext) {
            viewModelScope.ensureActive()
            val param = GetProductParam(productId)
            getProductUseCase.execute(param)
        }

    private suspend fun getPurchaseByProduct(productId: Int) =
        withContext(viewModelScope.coroutineContext) {
            viewModelScope.ensureActive()
            val param = PurchaseProductParam(productId)
            getPurchaseByProductUseCase.execute(param)
        }

    fun loadPurchaseItemDetailsAsync(recentPurchase: List<Purchase>) = viewModelScope.async(IO) {
        val purchaseItems = mutableListOf<PurchaseItem>()
        recentPurchase.forEach { purchase ->
            //Get buyer
            val purchaseByProduct = getPurchaseByProduct(purchase.productId)
            //Get details
            val productDetails = getProductDetails(purchase.productId)
            //Add to list item
            val buyerUsername = purchaseByProduct.map { it.username }
            purchaseItems.add(PurchaseItem(purchase, productDetails, buyerUsername))
        }

        //Sorting by product price order desc, then assign to liveData
        purchaseItems.sortByDescending { it.product.price }
        purchaseItemsLiveData.postValue(purchaseItems)
    }

    companion object {
        const val PURCHASE_FETCH_LIMIT = 5
    }
}

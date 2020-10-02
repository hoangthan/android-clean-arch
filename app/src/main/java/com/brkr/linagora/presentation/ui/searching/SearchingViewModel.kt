package com.brkr.linagora.presentation.ui.searching

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
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.ensureActive
import org.koin.core.KoinComponent
import org.koin.core.inject

class SearchingViewModel : ViewModel(), KoinComponent {

    val PURCHASE_FETCH_LIMIT = 5

    private val getUserUseCase: GetUserUseCase by inject()
    private val getPurchaseByUserUseCase: GetPurchaseByUserUseCase by inject()
    private val getPurchaseByProductUseCase: GetPurchaseByProductUseCase by inject()
    private val getProductUseCase: GetProductUseCase by inject()

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

    fun getProductDetails(productId: Int) = viewModelScope.async {
        viewModelScope.ensureActive()
        val param = GetProductParam(productId)
        getProductUseCase.execute(param)
    }

    fun getPurchaseByProduct(productId: Int) = viewModelScope.async {
        viewModelScope.ensureActive()
        val param = PurchaseProductParam(productId)
        getPurchaseByProductUseCase.execute(param)
    }
}

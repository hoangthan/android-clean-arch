package com.brkr.linagora.domain.interactor.impl

import com.brkr.linagora.domain.interactor.GetPurchaseByUserUseCase
import com.brkr.linagora.domain.interactor.GetPurchaseByUserUseCase.GetPurchaseParam
import com.brkr.linagora.domain.model.Purchase
import com.brkr.linagora.domain.repository.PurchaseRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

class GetPurchaseByUserUseCaseImpl : GetPurchaseByUserUseCase, KoinComponent {

    private val purchaseRepository: PurchaseRepository by inject()

    override suspend fun execute(param: GetPurchaseParam): List<Purchase> {
        return purchaseRepository.getListPurchaseByUsername(param.username, param.limit)
    }
}

package com.brkr.linagora.domain.interactor.impl

import com.brkr.linagora.domain.interactor.GetProductUseCase
import com.brkr.linagora.domain.interactor.GetProductUseCase.GetProductParam
import com.brkr.linagora.domain.model.Product
import com.brkr.linagora.domain.repository.ProductRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

class GetProductUseCaseImpl : GetProductUseCase, KoinComponent {

    private val productRepository: ProductRepository by inject()

    override suspend fun execute(param: GetProductParam): Product {
        return productRepository.findProductById(param.id)
    }
}

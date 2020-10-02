package com.brkr.linagora.domain.interactor

import com.brkr.linagora.domain.model.Product

interface GetProductUseCase {

    suspend fun execute(param: GetProductParam): Product?

    data class GetProductParam(
        val id: Int
    )
}

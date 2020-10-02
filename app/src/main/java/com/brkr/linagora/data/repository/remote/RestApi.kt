package com.brkr.linagora.data.repository.remote

import com.brkr.linagora.data.entity.GetPurchaseResponse
import com.brkr.linagora.data.entity.GetUserResponse
import com.brkr.linagora.data.entity.ProductDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestApi {

    @GET("users/{username}")
    suspend fun getUserByUsername(@Path("username") username: String): GetUserResponse

    @GET("purchases/by_user/{username}")
    suspend fun getPurchaseByUser(
        @Path("username") username: String,
        @Query("limit") limit: Int
    ): GetPurchaseResponse

    @GET("purchases/by_product/{productId}")
    suspend fun getPurchaseByProductId(@Path("productId") productId: Int): GetPurchaseResponse

    @GET("products/{productId}")
    suspend fun getProductDetails(@Path("productId") productId: Int): ProductDetailsResponse
}

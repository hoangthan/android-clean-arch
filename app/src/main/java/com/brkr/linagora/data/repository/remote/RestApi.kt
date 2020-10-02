package com.brkr.linagora.data.repository.remote

import com.brkr.linagora.data.entity.GetUserResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface RestApi {

    @GET("users/{username}")
    suspend fun getUserByUsername(@Path("username") username: String): GetUserResponse
}

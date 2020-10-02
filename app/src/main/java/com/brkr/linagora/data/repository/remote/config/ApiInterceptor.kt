package com.brkr.linagora.data.repository.remote.config

import okhttp3.Interceptor
import okhttp3.Response

object ApiInterceptor : Interceptor {

	//TODO: Add Authorization to header if needed

	override fun intercept(chain: Interceptor.Chain): Response {
		val newRequest = chain.request().newBuilder()
			.addHeader("Content-Type", "application/json")
			.build()

		return chain.proceed(newRequest)
	}
}

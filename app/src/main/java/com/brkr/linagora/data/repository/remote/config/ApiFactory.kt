package com.brkr.linagora.data.repository.remote.config

import com.brkr.linagora.BuildConfig
import com.brkr.linagora.data.repository.remote.RestApi
import okhttp3.CipherSuite
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.TlsVersion
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class ApiFactory {

	private val BASE_URL = BuildConfig.API_URL

	private val moshiConverterFactory: MoshiConverterFactory by lazy {
		MoshiConverterFactory.create()
	}

	private val okHttpClient by lazy {
		val httpLoggingInterceptor = HttpLoggingInterceptor()
		httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
		httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

		val okHttpBuilder = OkHttpClient.Builder()
			.connectTimeout(60L, TimeUnit.SECONDS)
			.writeTimeout(60L, TimeUnit.SECONDS)
			.readTimeout(60L, TimeUnit.SECONDS)
			.addInterceptor(ApiInterceptor)
			.addInterceptor(httpLoggingInterceptor)
			.followRedirects(true)
			.followSslRedirects(true)

		//Connection spec will be added if base url is https.
		if (BASE_URL.contains("https")) {
			okHttpBuilder.connectionSpecs(listOf(connectionSpec))
		}

		okHttpBuilder.build()
	}

	private val connectionSpec: ConnectionSpec by lazy {
		ConnectionSpec.Builder(ConnectionSpec.COMPATIBLE_TLS)
			.tlsVersions(TlsVersion.TLS_1_2, TlsVersion.TLS_1_1, TlsVersion.TLS_1_0)
			.cipherSuites(
				CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
				CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
				CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA,
				CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA,
				CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256,
				CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA,
				CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA,
				CipherSuite.TLS_DHE_DSS_WITH_3DES_EDE_CBC_SHA,
				CipherSuite.TLS_ECDHE_ECDSA_WITH_RC4_128_SHA,
				CipherSuite.TLS_DHE_RSA_WITH_AES_128_CBC_SHA,
				CipherSuite.TLS_DHE_DSS_WITH_AES_128_CBC_SHA,
				CipherSuite.TLS_DHE_RSA_WITH_AES_256_CBC_SHA,
				CipherSuite.TLS_ECDHE_RSA_WITH_RC4_128_SHA,
				CipherSuite.TLS_AES_128_CCM_8_SHA256
			)
			.build()
	}

	val instance: RestApi by lazy {
		Retrofit.Builder()
			.baseUrl(BASE_URL)
			.client(okHttpClient)
			.addConverterFactory(moshiConverterFactory)
			.build()
			.create(RestApi::class.java)
	}
}

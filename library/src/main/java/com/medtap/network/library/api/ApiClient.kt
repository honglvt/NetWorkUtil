package com.medtap.network.library.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient() {
    private val BASE_URL = "http://localhost:3000"
    private var retrofit: Retrofit? = null

    init {
        retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .client(InternalOkHttpClient.getOkhttpClient())
                .build()

    }

    private var apiService: MainService? = null

    companion object {
        val instance: ApiClient by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            ApiClient()
        }
    }

    fun getApiService(): MainService {
        return if (apiService == null) retrofit!!.create(MainService::class.java) else apiService!!
    }

}
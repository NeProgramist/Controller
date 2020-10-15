package com.agb.controller.framework.datasource.remote.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

open class ApiService {
    private val client: OkHttpClient
        get() = OkHttpClient().newBuilder()
            .authenticator(TokenAuthenticator())
            .build()

    protected val builder
        get(): Retrofit = Retrofit.Builder()
            .baseUrl("http://18.193.31.242:8453/api/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
}
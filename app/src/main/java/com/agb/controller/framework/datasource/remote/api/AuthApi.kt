package com.agb.controller.framework.datasource.remote.api

import com.agb.core.domain.model.Auth
import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi {
    @POST("oauth2/token")
    @FormUrlEncoded
    fun auth(
        @Field("grant_type") grantType: String = "password",
        @Field("username") username: String,
        @Field("password") password: String,
    ): Call<Auth>

    companion object Factory: ApiService() {
        operator fun invoke(): AuthApi = builder.create(AuthApi::class.java)
    }
}
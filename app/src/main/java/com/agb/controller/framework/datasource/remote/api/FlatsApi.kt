package com.agb.controller.framework.datasource.remote.api

import com.agb.core.domain.model.Flat
import com.agb.core.domain.model.Flats
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Streaming

interface FlatsApi {
    @GET("flats")
    @Streaming
    fun getFlats(): Observable<Response<List<Flat>>>

    @POST("flats")
    fun addFlat(
        @Body flat: Flat
    ): Single<Response<Flat>>

    companion object Factory: ApiService() {
        operator fun invoke(): FlatsApi = builder.create(FlatsApi::class.java)
    }
}
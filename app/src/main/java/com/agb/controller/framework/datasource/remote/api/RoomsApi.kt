package com.agb.controller.framework.datasource.remote.api

import com.agb.core.domain.model.Room
import com.agb.core.domain.model.Rooms
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface RoomsApi {
    @GET("rooms")
    fun getRooms(
        @Query("flatId") id: Int
    ): Single<Response<List<Room>>>

    @POST("rooms")
    fun addRoom(
        @Body room: Room
    ): Single<Response<Room>>

    @PUT("rooms/{id}")
    fun changeRoom(
        @Path("id") id: Int,
        @Body room: Room
    ): Single<Response<Unit>>

    @DELETE("rooms/{id}")
    fun deleteRoom(
        @Path("id") id: Int
    ): Single<Response<Unit>>

    companion object Factory: ApiService() {
        operator fun invoke(): RoomsApi = builder.create(RoomsApi::class.java)
    }
}
package com.agb.core.data

import com.agb.core.domain.model.Room
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface RoomsDataSource {
    fun getRooms(flatId: Int): Single<List<Room>>
    fun addRoom(room: Room): Single<Room>
    fun changeRoom(room: Room): Single<Boolean>
    fun deleteRoom(id: Int): Single<Boolean>
}
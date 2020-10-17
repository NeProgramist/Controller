package com.agb.core.domain.repository.rooms

import com.agb.core.domain.model.Room
import io.reactivex.rxjava3.core.Single

interface RoomsRepository {
    fun getRooms(flatId: Int): Single<List<Room>>
    fun addRoom(room: Room): Single<Room>
    fun changeRoom(room: Room): Single<Boolean>
    fun deleteRoom(id: Int): Single<Boolean>
}
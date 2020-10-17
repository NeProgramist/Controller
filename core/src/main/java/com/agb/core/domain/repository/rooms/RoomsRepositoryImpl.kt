package com.agb.core.domain.repository.rooms

import com.agb.core.data.RoomsDataSource
import com.agb.core.domain.model.Room
import io.reactivex.rxjava3.core.Single

class RoomsRepositoryImpl(
    private val remoteDataSource: RoomsDataSource
): RoomsRepository {
    override fun getRooms(flatId: Int): Single<List<Room>> = remoteDataSource.getRooms(flatId)
    override fun addRoom(room: Room): Single<Room> = remoteDataSource.addRoom(room)
    override fun changeRoom(room: Room): Single<Boolean> = remoteDataSource.changeRoom(room)
    override fun deleteRoom(id: Int): Single<Boolean> = remoteDataSource.deleteRoom(id)
}
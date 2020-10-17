package com.agb.controller.framework.datasource.remote

import com.agb.controller.framework.datasource.remote.api.RoomsApi
import com.agb.core.data.RoomsDataSource
import com.agb.core.domain.model.Room
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomsRemoteDataSource(
    private val api: RoomsApi
): RoomsDataSource {
    override fun getRooms(flatId: Int): Single<List<Room>> = Single.create {
        api.getRooms(flatId).subscribeOn(Schedulers.io()).subscribe({ rooms ->
            if (rooms.success) it.onSuccess(rooms.value)
            else it.onError(Throwable(rooms.error.errorCode))
        }, { er ->
            it.onError(er)
        })
    }

    override fun addRoom(room: Room): Single<Room> = Single.create {
        api.addRoom(room).subscribeOn(Schedulers.io()).subscribe({ room ->
            if (room.success) it.onSuccess(room.value)
            else it.onError(Throwable(room.error.errorCode))
        }, { er ->
            it.onError(er)
        })
    }

    override fun changeRoom(room: Room): Single<Boolean> = Single.create {
        api.changeRoom(room.id, room).subscribeOn(Schedulers.io()).subscribe({ room ->
            if (room.success) it.onSuccess(room.success)
            else it.onError(Throwable(room.error.errorCode))
        }, { er ->
            it.onError(er)
        })
    }

    override fun deleteRoom(id: Int): Single<Boolean> = Single.create {
        api.deleteRoom(id).subscribeOn(Schedulers.io()).subscribe({ room ->
            if (room.success) it.onSuccess(room.success)
            else it.onError(Throwable(room.error.errorCode))
        }, { er ->
            it.onError(er)
        })
    }
}
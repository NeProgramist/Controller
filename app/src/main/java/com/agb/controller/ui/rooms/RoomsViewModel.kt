package com.agb.controller.ui.rooms

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.agb.controller.framework.App
import com.agb.controller.framework.datasource.remote.RoomsRemoteDataSource
import com.agb.controller.framework.datasource.remote.api.RoomsApi
import com.agb.core.common.Result
import com.agb.core.domain.interactor.AddRoomUseCase
import com.agb.core.domain.interactor.ChangeRoomUseCase
import com.agb.core.domain.interactor.DeleteRoomUseCase
import com.agb.core.domain.interactor.GetRoomsUseCase
import com.agb.core.domain.model.Room
import com.agb.core.domain.repository.rooms.RoomsRepositoryImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

class RoomsViewModel : ViewModel() {
    val rooms = MutableLiveData<Result<List<Room>>>()
    val room = MutableLiveData<Result<Room>>()
    val change = MutableLiveData<Result<Room>>()
    val delete = MutableLiveData<Result<Int>>()

    fun getRooms(flatId: Int) {
        GetRoomsUseCase(
            RoomsRepositoryImpl(
                RoomsRemoteDataSource(
                    RoomsApi()
                )
            )
        )(flatId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                { rooms.value = Result.success(it) },
                { rooms.value = Result.error(it) }
            )
    }

    fun addRoom(data: Room) {
        AddRoomUseCase(
            RoomsRepositoryImpl(
                RoomsRemoteDataSource(
                    RoomsApi()
                )
            )
        )(data)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                { room.value = Result.success(it) },
                { room.value = Result.error(it) }
            )
    }

    fun changeRoom(data: Room) {
        ChangeRoomUseCase(
            RoomsRepositoryImpl(
                RoomsRemoteDataSource(
                    App.RoomsApi
                )
            )
        )(data)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                { change.value = Result.success(data) },
                { change.value = Result.error(it) }
            )
    }

    fun deleteRoom(roomId: Int) {
        DeleteRoomUseCase(
            RoomsRepositoryImpl(
                RoomsRemoteDataSource(
                    App.RoomsApi
                )
            )
        )(roomId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                { delete.value = Result.success(roomId) },
                { delete.value = Result.error(it) }
            )
    }
}

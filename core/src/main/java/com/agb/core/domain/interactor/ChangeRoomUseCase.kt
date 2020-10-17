package com.agb.core.domain.interactor

import com.agb.core.domain.model.Room
import com.agb.core.domain.repository.rooms.RoomsRepository
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class ChangeRoomUseCase(private val repository: RoomsRepository) {
    operator fun invoke(room: Room): Single<Boolean> = repository
        .changeRoom(room)
        .subscribeOn(Schedulers.io())
}
package com.agb.core.domain.interactor

import com.agb.core.domain.repository.rooms.RoomsRepository
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class DeleteRoomUseCase(private val repository: RoomsRepository) {
    operator fun invoke(id: Int): Single<Boolean> = repository
        .deleteRoom(id)
        .subscribeOn(Schedulers.io())
}
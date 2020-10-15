package com.agb.core.domain.interactor

import com.agb.core.domain.model.Flat
import com.agb.core.domain.repository.flats.FlatsRepository
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class GetFlatsUseCase(private val repository: FlatsRepository) {
    operator fun invoke(): Single<List<Flat>> = repository
        .getFlats()
        .subscribeOn(Schedulers.io())
}
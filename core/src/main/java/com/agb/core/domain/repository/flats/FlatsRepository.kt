package com.agb.core.domain.repository.flats

import com.agb.core.domain.model.Flat
import io.reactivex.rxjava3.core.Single

interface FlatsRepository {
    fun getFlats(): Single<List<Flat>>
    fun addFlat(flat: Flat): Single<Flat>
}
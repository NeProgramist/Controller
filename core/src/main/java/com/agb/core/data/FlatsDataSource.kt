package com.agb.core.data

import com.agb.core.domain.model.Flat
import io.reactivex.rxjava3.core.Single

interface FlatsDataSource {
    fun getFlats(): Single<List<Flat>>
    fun addFlat(flat: Flat): Single<Flat>
}
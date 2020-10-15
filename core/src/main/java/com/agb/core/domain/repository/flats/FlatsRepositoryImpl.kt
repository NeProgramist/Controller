package com.agb.core.domain.repository.flats

import com.agb.core.data.FlatsDataSource
import com.agb.core.domain.model.Flat
import io.reactivex.rxjava3.core.Single

class FlatsRepositoryImpl(
    private val remoteDataSource: FlatsDataSource
): FlatsRepository {
    override fun getFlats(): Single<List<Flat>> = remoteDataSource.getFlats()
    override fun addFlat(flat: Flat): Single<Flat> = remoteDataSource.addFlat(flat)
}
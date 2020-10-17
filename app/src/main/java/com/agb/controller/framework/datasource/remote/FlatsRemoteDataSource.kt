package com.agb.controller.framework.datasource.remote

import com.agb.controller.framework.datasource.remote.api.FlatsApi
import com.agb.core.data.FlatsDataSource
import com.agb.core.domain.model.Flat
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class FlatsRemoteDataSource(
    private val api: FlatsApi
): FlatsDataSource {
    override fun getFlats(): Single<List<Flat>> = Single.create {
        api.getFlats().subscribeOn(Schedulers.io()).subscribe({ flats ->
            if (flats.success) it.onSuccess(flats.value)
            else it.onError(Throwable(flats.error.errorCode))
        }, { er ->
            it.onError(er)
        })
    }

    override fun addFlat(flat: Flat): Single<Flat> = Single.create {
        api.addFlat(flat).subscribeOn(Schedulers.io()).subscribe({ flat ->
            if (flat.success) it.onSuccess(flat.value)
            else it.onError(Throwable(flat.error.errorCode))
        }, { er ->
            it.onError(er)
        })
    }
}

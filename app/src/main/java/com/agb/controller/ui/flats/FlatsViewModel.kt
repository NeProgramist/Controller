package com.agb.controller.ui.flats

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.agb.controller.framework.datasource.remote.FlatsRemoteDataSource
import com.agb.controller.framework.datasource.remote.api.FlatsApi
import com.agb.core.domain.interactor.AddFlatUseCase
import com.agb.core.domain.interactor.GetFlatsUseCase
import com.agb.core.domain.model.Flat
import com.agb.core.common.Result
import com.agb.core.domain.repository.flats.FlatsRepositoryImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

class FlatsViewModel : ViewModel() {
    val flats = MutableLiveData<Result<List<Flat>>>()
    val flat = MutableLiveData<Result<Flat>>()

    fun getFlats() {
        GetFlatsUseCase(
            FlatsRepositoryImpl(
                FlatsRemoteDataSource(
                    FlatsApi()
                )
            )
        )()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { flats.value = Result.success(it) },
                { flats.value = Result.error(it) }
            )
    }

    fun addFlat() {
        val mock = Flat(0, "405", "Some where in the space")
        AddFlatUseCase(
            FlatsRepositoryImpl(
                FlatsRemoteDataSource(
                    FlatsApi()
                )
            )
        )(mock)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { flat.value = Result.success(it) },
                { flats.value = Result.error(it) }
            )
    }
}

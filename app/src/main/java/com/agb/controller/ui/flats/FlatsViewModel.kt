package com.agb.controller.ui.flats

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.agb.controller.framework.App
import com.agb.controller.framework.datasource.remote.FlatsRemoteDataSource
import com.agb.controller.framework.datasource.remote.RoomsRemoteDataSource
import com.agb.controller.framework.datasource.remote.api.FlatsApi
import com.agb.controller.framework.datasource.remote.api.RoomsApi
import com.agb.core.domain.interactor.AddFlatUseCase
import com.agb.core.domain.interactor.GetFlatsUseCase
import com.agb.core.domain.model.Flat
import com.agb.core.common.Result
import com.agb.core.domain.interactor.AddRoomUseCase
import com.agb.core.domain.interactor.GetRoomsUseCase
import com.agb.core.domain.model.Room
import com.agb.core.domain.repository.flats.FlatsRepositoryImpl
import com.agb.core.domain.repository.rooms.RoomsRepositoryImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

class FlatsViewModel : ViewModel() {
    val flats = MutableLiveData<Result<List<Flat>>>()
    val flat = MutableLiveData<Result<Flat>>()

    fun getFlats() {
        GetFlatsUseCase(
            FlatsRepositoryImpl(
                FlatsRemoteDataSource(
                    App.FlatsApi
                )
            )
        )()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { flats.value = Result.success(it) },
                { flats.value = Result.error(it) }
            )
    }

    fun addFlat(data: Flat) {
        AddFlatUseCase(
            FlatsRepositoryImpl(
                FlatsRemoteDataSource(
                    App.FlatsApi
                )
            )
        )(data)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { flat.value = Result.success(it) },
                { flat.value = Result.error(it) }
            )
    }
}

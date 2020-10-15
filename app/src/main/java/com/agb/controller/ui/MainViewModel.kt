package com.agb.controller.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.agb.controller.framework.App
import com.agb.controller.framework.datasource.local.UserLocalDataSource
import com.agb.core.common.*
import com.agb.core.domain.interactor.GetUserUseCase
import com.agb.core.domain.model.User
import com.agb.core.domain.repository.user.UserRepositoryImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

class MainViewModel: ViewModel() {
    val user = MutableLiveData<Result<User>>()

    fun getUserData() {
        GetUserUseCase(
            UserRepositoryImpl(
                UserLocalDataSource(App.preferencesManager)
            )
        )()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                user.value = Result.success(it)
            }, {
                user.value = Result.error(it)
            })
    }
}
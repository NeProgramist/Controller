package com.agb.core.domain.interactor

import com.agb.core.domain.model.User
import com.agb.core.domain.repository.user.UserRepository
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class GetUserUseCase(private val repository: UserRepository) {
    operator fun invoke(): Single<User> = repository
        .getUserInfo()
        .subscribeOn(Schedulers.io())
}
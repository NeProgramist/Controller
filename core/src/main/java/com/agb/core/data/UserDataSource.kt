package com.agb.core.data

import com.agb.core.domain.model.User
import io.reactivex.rxjava3.core.Single

interface UserDataSource {
    fun getUserInfo(): Single<User>
    fun saveUserInfo(user: User)
}
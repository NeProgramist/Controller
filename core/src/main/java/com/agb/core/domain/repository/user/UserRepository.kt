package com.agb.core.domain.repository.user

import com.agb.core.common.Result
import com.agb.core.domain.model.User
import io.reactivex.rxjava3.core.Single

interface UserRepository {
    fun getUserInfo(): Single<User>
    fun saveUserInfo(user: User)
}
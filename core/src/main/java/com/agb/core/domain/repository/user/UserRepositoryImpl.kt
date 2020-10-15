package com.agb.core.domain.repository.user

import com.agb.core.common.Result
import com.agb.core.data.UserDataSource
import com.agb.core.domain.model.User
import io.reactivex.rxjava3.core.Single

class UserRepositoryImpl(
    private val localDataSource: UserDataSource
): UserRepository {
    override fun getUserInfo(): Single<User> = localDataSource.getUserInfo()
    override fun saveUserInfo(user: User) = localDataSource.saveUserInfo(user)
}
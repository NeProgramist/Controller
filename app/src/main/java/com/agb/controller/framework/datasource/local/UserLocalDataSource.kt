package com.agb.controller.framework.datasource.local

import com.agb.core.data.UserDataSource
import com.agb.core.domain.model.User
import io.reactivex.rxjava3.core.Single

class UserLocalDataSource(
    private val preferencesManager: PreferencesManager
): UserDataSource {
    override fun getUserInfo(): Single<User> = Single.create {
        with(preferencesManager) {
            val name = getString("nickname")
            val pass = getString("password")
            if (name == null || pass == null) it.onError(Throwable("no user in prefs"))
            else it.onSuccess(User(name, pass))
        }
    }

    override fun saveUserInfo(user: User) = with(preferencesManager) {
        putString("nickname", user.nickname)
        putString("password", user.password)
    }
}

package com.agb.controller.ui.login

import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.agb.controller.framework.App
import com.agb.controller.framework.datasource.local.UserLocalDataSource
import com.agb.core.domain.interactor.SaveUserUseCase
import com.agb.core.domain.model.User
import com.agb.core.domain.repository.user.UserRepositoryImpl

class LoginViewModel : ViewModel() {
    val name = MutableLiveData(false)
    val password = MutableLiveData(false)

    fun saveUserData(name: String, password: String) {
        SaveUserUseCase(
            UserRepositoryImpl(
                UserLocalDataSource(App.preferencesManager)
            )
        )(User(name, password))
    }

    /*
        just a primitive validator, can be easily changed to other pattern
     */
    val onNameChangeListener = object: TextWatcher {
        override fun afterTextChanged(s: Editable?) {}
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            name.value = s != null && s.length >= 3
        }
    }

    val onPasswordChangeListener = object: TextWatcher {
        override fun afterTextChanged(s: Editable?) {}
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            password.value = s != null && s.length >= 3
        }
    }


}

package com.agb.controller.ui

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.agb.controller.R
import com.agb.controller.framework.App
import com.agb.controller.framework.datasource.local.PreferencesManager
import com.agb.controller.ui.flats.FlatsFragment
import com.agb.controller.ui.flats.FlatsViewModel
import com.agb.controller.ui.login.LoginFragment
import com.agb.controller.ui.login.LoginViewModel
import com.agb.core.common.Result
import com.agb.core.common.Status
import com.agb.core.domain.model.User

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.user.observe(this, userObserver)
        viewModel.getUserData()
    }

    fun changeFragment(fragment: Fragment) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment_container, fragment)
        ft.commit()
    }

    private val userObserver = Observer<Result<User>> { result ->
        if (result.status == Status.SUCCESS) changeFragment(FlatsFragment())
        else changeFragment(LoginFragment())
    }
}

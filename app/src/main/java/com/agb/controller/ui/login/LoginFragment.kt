package com.agb.controller.ui.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.agb.controller.R
import com.agb.controller.ui.MainActivity
import com.agb.controller.ui.flats.FlatsFragment
import kotlinx.android.synthetic.main.login_fragment.*


class LoginFragment : Fragment() {
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        //viewModel.name.observe(viewLifecycleOwner, nameValidatorObserver)
        //viewModel.password.observe(viewLifecycleOwner, passwordValidatorObserver)

        log_btn.setOnClickListener {
            viewModel.saveUserData(username_edt.text.toString(), password_edt.text.toString())
            (activity as MainActivity).changeFragment(FlatsFragment())
        }
    }

    private val validatorObserver = Observer<Boolean> {
        log_btn.isEnabled = it
    }
}

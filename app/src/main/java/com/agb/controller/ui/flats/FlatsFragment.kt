package com.agb.controller.ui.flats

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class FlatsFragment : Fragment() {

    private lateinit var viewModel: FlatsViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FlatsViewModel::class.java)

    }

}

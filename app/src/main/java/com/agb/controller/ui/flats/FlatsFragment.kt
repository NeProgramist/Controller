package com.agb.controller.ui.flats

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.agb.controller.R
import com.agb.controller.framework.datasource.remote.api.FlatsApi
import com.agb.core.common.Result
import com.agb.core.common.Status
import com.agb.core.domain.model.Flat
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.dialog_new_flat.*
import kotlinx.android.synthetic.main.flats_fragment.*

class FlatsFragment : Fragment(), PassFlatContract, FlatsAdapter.OnItemClickListener {
    private lateinit var viewModel: FlatsViewModel
    private lateinit var flatsAdapter: FlatsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.flats_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        flatsAdapter = FlatsAdapter(this)
        viewModel = ViewModelProvider(this).get(FlatsViewModel::class.java)
        viewModel.flats.observe(viewLifecycleOwner, flatsObserver)
        viewModel.flat.observe(viewLifecycleOwner, flatObserver)

        flat_rv.adapter = flatsAdapter
        flat_rv.layoutManager = LinearLayoutManager(activity)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.home -> {
                    //TODO(handle home bottom navigation click)
                    true
                }
                R.id.settings -> {
                    //TODO(handle settings bottom navigation click)
                    true
                }
                else -> false
            }
        }


        add_new_flat_btn.setOnClickListener {
            activity?.supportFragmentManager?.let {
                val dialog = NewFlatFragment()
                dialog.setTargetFragment(this, 0)
                dialog.show(it, null)
            }
        }

        viewModel.getFlats()
    }

    override fun getData(address: String, flat: String) {
        val new = Flat(0, address, flat)
        viewModel.addFlat(new)
    }

    override fun onClickListener(id: Int) {
        viewModel.getRoom(id)
    }

    private val flatsObserver = Observer<Result<List<Flat>>> { flats ->
        flats.data?.let { if (flats.status == Status.SUCCESS) flatsAdapter.addFlats(it) }
    }

    private val flatObserver = Observer<Result<Flat>> { flat ->
        flat.data?.let { if (flat.status == Status.SUCCESS) flatsAdapter.addFlat(it) }
    }

}

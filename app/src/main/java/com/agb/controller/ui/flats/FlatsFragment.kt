package com.agb.controller.ui.flats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.agb.controller.R
import com.agb.controller.framework.datasource.remote.api.FlatsApi
import com.agb.core.common.Result
import com.agb.core.common.Status
import com.agb.core.domain.model.Flat
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.flats_fragment.*

class FlatsFragment : Fragment() {
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

        flatsAdapter = FlatsAdapter()
        viewModel = ViewModelProvider(this).get(FlatsViewModel::class.java)
        viewModel.flats.observe(viewLifecycleOwner, flatsObserver)
        viewModel.flat.observe(viewLifecycleOwner, flatObserver)

        flat_rv.adapter = flatsAdapter
        flat_rv.layoutManager = LinearLayoutManager(activity)
        viewModel.getFlats()
    }

    private val flatsObserver = Observer<Result<List<Flat>>> { flats ->
        flats.data?.let { if (flats.status == Status.SUCCESS) flatsAdapter.addFlats(it) }
    }

    private val flatObserver = Observer<Result<Flat>> { flat ->
        flat.data?.let { if (flat.status == Status.SUCCESS) flatsAdapter.addFlat(it) }
    }
}

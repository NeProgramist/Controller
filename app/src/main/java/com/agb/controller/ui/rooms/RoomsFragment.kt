package com.agb.controller.ui.rooms

import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.agb.controller.R
import com.agb.core.common.Result
import com.agb.core.common.Status
import com.agb.core.domain.model.Room
import kotlinx.android.synthetic.main.flat.*

class RoomsFragment :
    Fragment(),
    PassRoomContract,
    RoomsAdapterSeparated.OnItemClick,
    RoomsAdapterSeparated.OnButtonClick {
    private var flatId: Int = -1
    private lateinit var viewModel: RoomsViewModel
    private lateinit var roomsAdapter: RoomsAdapterSeparated

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inf = TransitionInflater.from(requireContext())
        enterTransition = inf.inflateTransition(R.transition.slide_down)
        exitTransition = inf.inflateTransition(R.transition.slide_top)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.rooms_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RoomsViewModel::class.java)
        viewModel.rooms.observe(viewLifecycleOwner, roomsObserver)
        viewModel.room.observe(viewLifecycleOwner, roomObserver)
        viewModel.change.observe(viewLifecycleOwner, changeObserver)
        viewModel.delete.observe(viewLifecycleOwner, deleteObserver)
        roomsAdapter = RoomsAdapterSeparated(this, this)

        room_rv.adapter = roomsAdapter
        room_rv.layoutManager = GridLayoutManager(activity, 2)

        arguments?.getInt("id")?.let {
            flatId = it
            viewModel.getRooms(it)
        }
    }

    override fun getData(name: String) {
        val new = Room(flatId,0, name)
        viewModel.addRoom(new)
    }

    override fun changeData(id: Int, name: String) {
        val changed = Room(flatId, id, name)
        viewModel.changeRoom(changed)
    }

    override fun deleteData(id: Int) {
        viewModel.deleteRoom(id)
    }

    override fun onItemClick(roomId: Int) {
        activity?.supportFragmentManager?.let {
            val dialog = ChangeRoomFragment()
            val args = Bundle()
            args.putInt("id", roomId)

            dialog.arguments = args
            dialog.setTargetFragment(this, 0)
            dialog.show(it, null)
        }
    }

    override fun onButtonClick() {
        activity?.supportFragmentManager?.let {
            val dialog = NewRoomFragment()
            dialog.setTargetFragment(this, 0)
            dialog.show(it, null)
        }
    }

    private val roomsObserver = Observer<Result<List<Room>>> { rooms ->
        rooms.data?.let {
            if (rooms.status == Status.SUCCESS) {
                roomsAdapter.addRooms(it)
            }
        }
    }

    private val roomObserver = Observer<Result<Room>> { room ->
        room.data?.let {
            if (room.status == Status.SUCCESS) {
                roomsAdapter.addRoom(it)
            }
        }
    }

    private val changeObserver = Observer<Result<Room>> { room ->
        room.data?.let {
            if (room.status == Status.SUCCESS) {
                roomsAdapter.changeRoom(it)
            }
        }
    }

    private val deleteObserver = Observer<Result<Int>> { id ->
        id.data?.let {
            if (id.status == Status.SUCCESS) {
                roomsAdapter.deleteRoom(it)
            }
        }
    }
}

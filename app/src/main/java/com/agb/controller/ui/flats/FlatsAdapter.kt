package com.agb.controller.ui.flats

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.agb.controller.R
import com.agb.controller.ui.rooms.RoomsAdapter
import com.agb.controller.ui.rooms.RoomsAdapterSeparated
import com.agb.core.domain.model.Flat
import com.agb.core.domain.model.Room
import kotlinx.android.synthetic.main.flat.view.*

class FlatsAdapter(
    private val onItemClick: OnItemClickListener,
    private val onItemAddRoomButtonClickListener: OnAddRoomButtonClickListener,
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var flatsHolder = ArrayList<Flat>()
    private var roomsAdapter = ArrayList<RoomsAdapter>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val item = layoutInflater.inflate(R.layout.flat, parent, false)
        return FlatViewHolder(item, onItemClick, onItemAddRoomButtonClickListener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FlatViewHolder) {
            holder.bind(flatsHolder[position], position)
        }
    }

    override fun getItemCount() = flatsHolder.size

    fun addFlat(flat: Flat) {
        flatsHolder.add(flat)
        notifyItemInserted(flatsHolder.size - 1)
    }

    fun addFlats(flats: List<Flat>) {
        val startIndex = flats.size - 1
        flatsHolder.addAll(flats)
        notifyItemRangeInserted(startIndex, flats.size)
    }

    fun addRooms(rooms: List<Room>) {
        if (rooms.isNotEmpty()) {
            val index = flatsHolder.indexOf(flatsHolder.find { it.id == rooms[0].flatId })
            roomsAdapter[index].addRooms(rooms)
        }
    }

    fun addRoom(room: Room) {
        val index = flatsHolder.indexOf(flatsHolder.find { it.id == room.flatId})
        roomsAdapter[index].addRoom(room)
    }

    fun clear() {
        val size = flatsHolder.size
        flatsHolder.clear()
        notifyItemRangeRemoved(0, size - 1)
    }

    inner class FlatViewHolder(
        private val item: View,
        private val onItemClick: OnItemClickListener,
        private val onItemAddRoomButtonClickListener: OnAddRoomButtonClickListener,
    ): RecyclerView.ViewHolder(item) {
        fun bind(flat: Flat, id: Int) {
            item.flat_address_txt.text = flat.name
            item.flat_flat_txt.text = flat.address

            val lm = GridLayoutManager(item.context, 2)
            item.room_rv.apply {
                layoutManager = lm
                val ad = RoomsAdapter()
                roomsAdapter.add(id, ad)
                adapter = ad
            }

            item.setOnClickListener {
                 if (item.room_rv.visibility == View.GONE) {
                     onItemClick.onClickListener(flatsHolder[adapterPosition].id)
                     item.room_rv.visibility = View.VISIBLE
                 } else item.room_rv.visibility = View.GONE
            }
        }

    }

    interface OnItemClickListener {
        fun onClickListener(id: Int)
    }

    interface OnAddRoomButtonClickListener {
        fun onAddRoomButtonListener(flatId: Int)
    }
}

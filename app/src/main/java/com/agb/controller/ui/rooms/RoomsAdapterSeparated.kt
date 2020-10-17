package com.agb.controller.ui.rooms

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.agb.controller.R
import com.agb.core.domain.model.Room
import kotlinx.android.synthetic.main.room.view.*

class RoomsAdapterSeparated(
    private val onItemClick: OnItemClick,
    private val onButtonClick: OnButtonClick,
): RecyclerView.Adapter<RecyclerView.ViewHolder>()  {
    private val roomHolder = ArrayList<Room>()
    private val mock = Room(-1, -1, "")


    override fun getItemViewType(position: Int): Int {
        return if (position == roomHolder.size - 1) 1 else 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return if (viewType == 0) {
            val item = layoutInflater.inflate(R.layout.room, parent, false)
            RoomViewHolder(item, onItemClick)
        } else {
            val item = Button(parent.context);
            AddRoomViewHolder(item, onButtonClick)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is RoomViewHolder) holder.bind(roomHolder[position])
        else if (holder is AddRoomViewHolder) holder.bind()
    }

    override fun getItemCount(): Int = roomHolder.size

    fun addRooms(rooms: List<Room>) {
        roomHolder.clear()
        roomHolder.addAll(rooms)
        roomHolder.add(mock) // just get place for add button
        notifyDataSetChanged()
    }

    fun addRoom(room: Room) {
        val position = roomHolder.size - 1
        roomHolder[position] = room
        notifyItemChanged(position)
        roomHolder.add(mock)
        notifyItemInserted(position + 1)
    }

    fun changeRoom(changed: Room) {
        val room = roomHolder.find { it.id == changed.id }
        val index = roomHolder.indexOf(room)

        roomHolder[index] = changed
        notifyItemChanged(index)
    }

    fun deleteRoom(id: Int) {
        val room = roomHolder.find { it.id == id }
        val index = roomHolder.indexOf(room)

        roomHolder.removeAt(index)
        notifyItemRemoved(index)
    }

    inner class RoomViewHolder(
        private val item: View,
        private val onItemClick: OnItemClick,
    ): RecyclerView.ViewHolder(item) {
        fun bind(room: Room) {
            item.room_txt.text = room.name
            item.setOnClickListener {
                onItemClick.onItemClick(room.id)
            }
        }
    }

    inner class AddRoomViewHolder(
        private val item: View,
        private val onButtonClick: OnButtonClick,
    ): RecyclerView.ViewHolder(item) {
        fun bind() {
            item.background = ContextCompat.getDrawable(item.context, R.drawable.add_new_room_btn)
            item.setOnClickListener {
                onButtonClick.onButtonClick()
            }
        }
    }

    interface OnItemClick {
        fun onItemClick(roomId: Int)
    }

    interface OnButtonClick {
        fun onButtonClick()
    }

}
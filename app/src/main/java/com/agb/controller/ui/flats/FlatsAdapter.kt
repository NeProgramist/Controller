package com.agb.controller.ui.flats

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.agb.controller.R
import com.agb.core.domain.model.Flat
import kotlinx.android.synthetic.main.flat.view.*

class FlatsAdapter(
    private val onItemClick: OnItemClickListener
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var flatsHolder = ArrayList<Flat>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val item = layoutInflater.inflate(R.layout.flat, parent, false)
        return FlatViewHolder(item, onItemClick)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FlatViewHolder) {
            holder.bind(flatsHolder[position])
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

    fun clear() {
        val size = flatsHolder.size
        flatsHolder.clear()
        notifyItemRangeRemoved(0, size - 1)
    }

    inner class FlatViewHolder(
        private val item: View,
        private val onItemClick: OnItemClickListener
    ): RecyclerView.ViewHolder(item) {
        fun bind(flat: Flat) {
            item.flat_address_txt.text = flat.name
            item.flat_flat_txt.text = flat.address

            item.setOnClickListener {
                onItemClick.onClickListener(flatsHolder[adapterPosition].id)
            }
        }
    }

    interface OnItemClickListener {
        fun onClickListener(id: Int)
    }
}

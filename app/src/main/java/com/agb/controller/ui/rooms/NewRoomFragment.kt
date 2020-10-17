package com.agb.controller.ui.rooms


import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.agb.controller.R
import kotlinx.android.synthetic.main.dialog_new_room.*

class NewRoomFragment: DialogFragment() {
    private lateinit var dg: Dialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_new_room, container, false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.setBackgroundDrawableResource(R.drawable.dialog_background)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dg = dialog
        return dialog
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dialog_add_btn.setOnClickListener {
            (targetFragment as PassRoomContract).getData(dialog_room_edt.text.toString())
            dialog?.dismiss()
        }
    }
}

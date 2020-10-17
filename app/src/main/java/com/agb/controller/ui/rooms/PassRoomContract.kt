package com.agb.controller.ui.rooms

interface PassRoomContract {
    fun getData(name: String)
    fun changeData(id: Int, name:String)
    fun deleteData(id: Int)
}
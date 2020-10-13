package com.agb.core.domain.model

data class Rooms(
    val error: Error,
    val success: Boolean,
    val value: List<Room>
)
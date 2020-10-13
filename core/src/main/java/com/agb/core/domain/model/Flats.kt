package com.agb.core.domain.model

data class Flats(
    val error: Error,
    val success: Boolean,
    val flat: List<Flat>
)
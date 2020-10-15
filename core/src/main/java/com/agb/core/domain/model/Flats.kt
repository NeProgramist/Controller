package com.agb.core.domain.model

data class Flats(
    val error: Error,
    val success: Boolean,
    val value: List<Flat>
)
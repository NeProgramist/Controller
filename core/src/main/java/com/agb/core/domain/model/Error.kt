package com.agb.core.domain.model

data class Error(
    val errorCode: String,
    val validationErrors: List<ValidationError>
)
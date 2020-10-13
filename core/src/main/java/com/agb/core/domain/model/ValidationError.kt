package com.agb.core.domain.model

data class ValidationError(
    val errorCode: String,
    val errorMessage: String,
    val formattedMessagePlaceholderValues: FormattedMessagePlaceholderValues,
    val propertyName: String,
    val severity: String
)
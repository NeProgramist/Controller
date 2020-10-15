package com.agb.controller.framework.datasource.remote.api
import com.agb.core.domain.model.Error

data class Response<T>(
    val value: T,
    val success: Boolean,
    val error: Error
)
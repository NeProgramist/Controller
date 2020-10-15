package com.agb.core.common

data class Result<out T>(val status: Status, val data: T?, val error: Throwable?) {
    companion object {
        fun <T> success(data: T?): Result<T> = Result(Status.SUCCESS, data, null)
        fun <T> error(error: Throwable?): Result<T> = Result(Status.ERROR, null, error)
    }

    override fun toString() = when (this.status) {
        Status.SUCCESS -> "Success[data: $data]"
        Status.ERROR -> "Error[error: $error]"
    }
}

enum class Status {
    SUCCESS,
    ERROR
}


package com.emirhan.trendyolcaseapp.utils

class Result<out T>(val status: Status, val data: T?) {
    companion object {
        fun <T> success(data: T?): Result<T> = Result(Status.SUCCESS, data)

        fun loading() = Result(Status.LOADING, null)

        fun <T> error(message: String?): Result<T> = Result(Status.ERROR, null)
    }
}

enum class Status {
    SUCCESS,
    LOADING,
    ERROR
}
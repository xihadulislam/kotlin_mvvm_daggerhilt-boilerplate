package com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.utils.ext



data class ResponseState<out T>(val status: Status, val data: T?, val message: String?) {

    companion object {

        fun <T> success(data: T?): ResponseState<T> {
            return ResponseState(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): ResponseState<T> {
            return ResponseState(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): ResponseState<T> {
            return ResponseState(Status.LOADING, data, null)
        }

    }

}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}
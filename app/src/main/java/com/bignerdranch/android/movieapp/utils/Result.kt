package com.bignerdranch.android.movieapp.utils

//Result manages the status
class Result<out T>(val status: Status, val data: T?, message: String?) {


    companion object {
        //if success/error then hide the progress bar
        fun <T> success(data: T?): Result<T> {
            return Result(Status.SUCCESS, data, null)
        }

        //if loading then progress bar will be visible
        fun <T> loading(message: String?): Result<T> {
            return Result(Status.LOADING, null, message)
        }

        fun <T> error(message: String?): Result<T> {
            return Result(Status.ERROR, null, message)
        }
    }

}

enum class Status {
    SUCCESS,
    LOADING,
    ERROR
}
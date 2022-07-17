package com.nishant.pullrequestsviewer.utils

sealed class Resource<T>(
    val data: T? = null,
    val status: STATUS,
    val message: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data, STATUS.SUCCESS, null)
    class Loading<T>(data: T?) : Resource<T>(data, STATUS.LOADING, null)
    class Error<T>(message: String?) : Resource<T>(null, STATUS.ERROR, message)
}

enum class STATUS {
    SUCCESS,
    LOADING,
    ERROR
}

package org.unicorn.whiteboard.common.http.model


sealed class BaseResult<out T > {

    data class Success<out T >(val data: T) : BaseResult<T>()

    data class Error(val exception: Exception) : BaseResult<Nothing>()

}

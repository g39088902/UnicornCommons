package org.unicorn.whiteboard.common.http.model


sealed class ResponseResult

data class ResponseSuccess<T>(val data: T?) : ResponseResult()
data class ResponseError(val t: Throwable?) : ResponseResult()
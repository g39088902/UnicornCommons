package org.unicorn.whiteboard.common.http.model


data class BaseResponse<out T>(val code: Int, val msg: String, val data: T)
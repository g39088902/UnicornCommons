package org.unicorn.whiteboard.common.http.exception

class ResultException(var code: String, var msg: String) : Exception(msg)

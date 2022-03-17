package org.unicorn.whiteboard.common.flowevent

import java.util.logging.Level

/**
 * create by tlq,on 2022/3/1
 * Desc:
 */
interface ILogger {
    fun log(level: Level, msg: String)
    fun log(level: Level, msg: String, th: Throwable)
}
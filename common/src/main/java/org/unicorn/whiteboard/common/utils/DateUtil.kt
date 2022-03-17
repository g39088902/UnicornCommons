package org.unicorn.whiteboard.common.utils

import java.util.concurrent.TimeUnit

object DateUtil {


    /**
     * second 总秒数
     * */
    fun parseSecondToTime(second: Int): String {

        val DAY = second / 60 / 60 / 24
        val HOURS = second / 60 / 60
        val MINUTE = second / 60
        val SECOND = second % 60

        var str: String = ""
        if (DAY > 0) {
            str += String.format("%03d日", DAY)
        }
        if (DAY > 0 || HOURS > 0) {
            str += String.format("%02d时", HOURS)
        }
        if (DAY > 0 || HOURS > 0 || MINUTE > 0) {
            str += String.format("%02d分", MINUTE)
        }
        if (SECOND > 0) {
            str += String.format("%02d秒", SECOND)
        }

        return str
    }

}
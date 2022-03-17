package org.unicorn.whiteboard.common.flowevent

import android.app.Application

/**
 * create by tlq,on 2022/3/1
 * Desc:
 */
object FlowEventInitializer {

    lateinit var application: Application

    var logger: ILogger? = null

    fun init(application: Application, logger: ILogger? = null) {
        FlowEventInitializer.application = application
        this.logger = logger
    }

}
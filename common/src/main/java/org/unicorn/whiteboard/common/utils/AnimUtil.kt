package org.unicorn.whiteboard.common.utils

import android.content.Context
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils

/**
 * 控件平移动画
 */
object AnimUtil {


    /**
     * 平移动画
     */
    fun translateAnim(
        context: Context,
        view: View,
        animId: Int,
        repeatCount: Int = 1,
        fillAfter: Boolean = true,
        listener: Animation.AnimationListener? = null
    ) {
        val animation: Animation =
            AnimationUtils.loadAnimation(context, animId).apply {
                this.repeatCount = repeatCount   //动画的重复次数
                this.fillAfter = fillAfter //设置为true，动画转化结束后被应用
                setAnimationListener(listener)
            }
        view.startAnimation(animation)
    }


}
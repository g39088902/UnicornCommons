package org.unicorn.whiteboard.common.utils

import android.graphics.Bitmap
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.drawable.Drawable
import org.unicorn.whiteboard.common.CommonApplication
import org.unicorn.whiteboard.common.R


object BitmapUtils {

    var penMode = true

    // 1120*(1-1/√2)=41*(16-4)
    val displayWidth = 1120
    val scaleStepWidth = 41
    val scalethreshold = 8

    fun newBitmap(width: Int = 100, height: Int = 100) =
        Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)

    fun getExpWidth(size: Int) =
        if (size <= scalethreshold) displayWidth else displayWidth - (size - scalethreshold) * scaleStepWidth

    fun getExpHeight(resW: Int, resH: Int, expW: Int) =
        (resH * (expW.toFloat() / resW.toFloat())).toInt()

    fun mainBlue(): Int = CommonApplication.context.resources.getColor(R.color.mainBlue, null)

    fun textColor(): Int =
        CommonApplication.context.resources.getColor(R.color.default_text_color, null)

    fun Drawable.applyColor(color: Int) {
        mutate().colorFilter = BlendModeColorFilter(
            color,
            BlendMode.SRC_IN
        )
    }


}
package org.unicorn.whiteboard.common.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import org.unicorn.whiteboard.common.databinding.PlaceholderViewBinding

/**
 * create by tlq,on 2022/3/3
 * Desc:
 */
class PlaceholderViewViewHolder(val bind: PlaceholderViewBinding) :
    RecyclerView.ViewHolder(bind.root) {

    fun init( onClickListener: ((View) -> Unit)? = null) {
        bind.clPlaceholder.setOnClickListener {
            onClickListener?.invoke(it)
        }
    }


}
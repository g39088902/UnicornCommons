package org.unicorn.whiteboard.common.recyclerview

import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import org.unicorn.whiteboard.common.ktx.BindingViewHolder
import org.unicorn.whiteboard.common.recyclerview.ItemViewType.EMPTY_VIEW
import org.unicorn.whiteboard.common.recyclerview.ItemViewType.FOOTER_VIEW
import org.unicorn.whiteboard.common.recyclerview.ItemViewType.HEADER_VIEW
import org.unicorn.whiteboard.common.recyclerview.ItemViewType.LOAD_MORE_VIEW

/**
 * create by tlq,on 2022/3/11
 * Desc:
 */
abstract class BaseAdapter<DT, VH : ViewBinding> @JvmOverloads constructor(
    @LayoutRes private val layoutResId: Int,
    data: MutableList<DT>? = null
) : RecyclerView.Adapter<BindingViewHolder<VH>>() {

    var mData: MutableList<DT> = data ?: arrayListOf()
        internal set

    private lateinit var mHeaderLayout: LinearLayout
    private lateinit var mFooterLayout: LinearLayout
    private lateinit var mEmptyLayout: FrameLayout

    var isUseEmptyLayout = true

    /**
     * 当显示空布局时，是否显示 Header
     */
    var isEmptyWithHeader: Boolean = false


    override fun onBindViewHolder(holder: BindingViewHolder<VH>, position: Int) {
        when (holder.itemViewType) {
            LOAD_MORE_VIEW -> {

            }
            HEADER_VIEW, EMPTY_VIEW, FOOTER_VIEW -> return

            else -> {
            }
        }


    }

    override fun getItemViewType(position: Int): Int {
        if (hasEmptyView()) {
            val header = isEmptyWithHeader && hasHeaderLayout()
            return when (position) {
                0 -> if (header) {
                    HEADER_VIEW
                } else {
                    EMPTY_VIEW
                }
                1 -> if (header) {
                    EMPTY_VIEW
                } else {
                    FOOTER_VIEW
                }
                2 -> FOOTER_VIEW
                else -> EMPTY_VIEW
            }
        }

        val hasHeader = hasHeaderLayout()
        if (hasHeader && position == 0) {
            return HEADER_VIEW
        } else {
            var adjPosition = if (hasHeader) {
                position - 1
            } else {
                position
            }
            val dataSize = mData.size
            return if (adjPosition < dataSize) {
                getDefItemViewType(adjPosition)
            } else {
                adjPosition -= dataSize
                LOAD_MORE_VIEW

            }
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    protected open fun getDefItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    /**
     * 使用新的数据集合，改变原有数据集合内容。
     * 注意：不会替换原有的内存引用，只是替换内容
     *
     */
    open fun setList(list: Collection<DT>?) {
        if (list !== this.mData) {
            this.mData.clear()
            if (!list.isNullOrEmpty()) {
                this.mData.addAll(list)
            }
        } else {
            if (!list.isNullOrEmpty()) {
                val newList = ArrayList(list)
                this.mData.clear()
                this.mData.addAll(newList)
            } else {
                this.mData.clear()
            }
        }

        list?.let { notifyItemRangeChanged(0, it.size) }
    }

    fun hasEmptyView(): Boolean {
        if (!this::mEmptyLayout.isInitialized || mEmptyLayout.childCount == 0) {
            return false
        }
        if (!isUseEmptyLayout) {
            return false
        }
        return mData.isEmpty()
    }

    fun hasHeaderLayout(): Boolean {
        if (this::mHeaderLayout.isInitialized && mHeaderLayout.childCount > 0) {
            return true
        }
        return false
    }


    private var onItemClickListener: ((View, Int) -> Unit)? = null


}
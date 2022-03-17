package org.unicorn.whiteboard.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.launcher.ARouter

abstract class BaseFragment(@LayoutRes val layoutId: Int) : Fragment(layoutId) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ARouter.getInstance().inject(this)
        initView()

    }

    abstract fun initView()

}
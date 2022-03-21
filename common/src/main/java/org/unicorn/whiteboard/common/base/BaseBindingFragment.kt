

@file:Suppress("unused")

package org.unicorn.whiteboard.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.alibaba.android.arouter.launcher.ARouter
import com.bugsnag.android.Bugsnag
import org.unicorn.whiteboard.common.utils.ViewBindingUtil

abstract class BaseBindingFragment<VB : ViewBinding> : Fragment() {

  private var _binding: VB? = null
  val binding: VB get() = _binding!!

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    _binding = ViewBindingUtil.inflateWithGeneric(this, inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    Bugsnag.start(requireContext())
    ARouter.getInstance().inject(this)

    initView()

  }

  abstract fun initView()


  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}
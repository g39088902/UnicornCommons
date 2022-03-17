

package org.unicorn.whiteboard.common.ktx

import android.view.View
import androidx.viewbinding.ViewBinding

fun <VB : ViewBinding> View.getBinding(bind: (View) -> VB): VB =
  (bindingCache as? VB) ?: bind(this).also { binding -> bindingCache = binding }

private var View.bindingCache: Any?
  get() = getTag(Int.MIN_VALUE)
  set(value) = setTag(Int.MIN_VALUE, value)

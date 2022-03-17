package org.unicorn.whiteboard.common.ktx

import android.app.Activity
import android.app.Dialog
import android.view.LayoutInflater
import androidx.activity.ComponentActivity
import androidx.viewbinding.ViewBinding


fun <VB : ViewBinding> ComponentActivity.binding(
    inflate: (LayoutInflater) -> VB,
    setContentView: Boolean = true
) = lazy {
    inflate(layoutInflater).also { binding ->
        if (setContentView) setContentView(binding.root)
    }
}



///////////////////////////////////////////////ViewBinding///////////////////////////////////
inline fun <reified VB : ViewBinding> Activity.inflate() = lazy {
    inflateBinding<VB>(layoutInflater).apply { setContentView(root) }
}

inline fun <reified VB : ViewBinding> Dialog.inflate() = lazy {
    inflateBinding<VB>(layoutInflater).apply { setContentView(root) }
}

@Suppress("UNCHECKED_CAST")
inline fun <reified VB : ViewBinding> inflateBinding(layoutInflater: LayoutInflater) =
    VB::class.java.getMethod("inflate", LayoutInflater::class.java)
        .invoke(null, layoutInflater) as VB





package org.unicorn.whiteboard.common.ktx

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * create by tlq,on 2022/2/23
 * Desc:
 */

fun <VB : ViewBinding> Fragment.binding(bind: (View) -> VB) = FragmentBindingDelegate(bind)

fun <VB : ViewBinding> Fragment.binding(inflate: (LayoutInflater) -> VB) = FragmentInflateBindingDelegate(inflate)

class FragmentBindingDelegate<VB : ViewBinding>(private val bind: (View) -> VB) : ReadOnlyProperty<Fragment, VB> {
    override fun getValue(thisRef: Fragment, property: KProperty<*>): VB {
        val binding = try {
            thisRef.requireView().getBinding(bind)
        } catch (e: IllegalStateException) {
            throw IllegalStateException("The property of ${property.name} has been destroyed.")
        }
        return binding
    }
}

class FragmentInflateBindingDelegate<VB : ViewBinding>(private val inflate: (LayoutInflater) -> VB) : ReadOnlyProperty<Fragment, VB> {
    private var binding: VB? = null
    private val handler by lazy { Handler(Looper.getMainLooper()) }

    override fun getValue(thisRef: Fragment, property: KProperty<*>): VB {
        if (binding == null) {
            binding = try {
                inflate(thisRef.layoutInflater)
            } catch (e: IllegalStateException) {
                throw IllegalStateException("The property of ${property.name} has been destroyed.")
            }
            thisRef.viewLifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
                override fun onDestroy(owner: LifecycleOwner) {
                    handler.post { binding = null }
                }
            })
        }
        return binding!!
    }
}
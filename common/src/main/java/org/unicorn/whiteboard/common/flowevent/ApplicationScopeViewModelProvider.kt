package org.unicorn.whiteboard.common.flowevent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

/**
 * create by tlq,on 2022/3/1
 * Desc:
 */
object ApplicationScopeViewModelProvider : ViewModelStoreOwner {

    private val eventViewModelStore: ViewModelStore = ViewModelStore()

    override fun getViewModelStore(): ViewModelStore {
        return eventViewModelStore
    }

    private val mApplicationProvider: ViewModelProvider by lazy {
        ViewModelProvider(
            ApplicationScopeViewModelProvider,
            ViewModelProvider.AndroidViewModelFactory.getInstance(FlowEventInitializer.application)
        )
    }

    fun <T : ViewModel> getApplicationProvider(modelClass: Class<T>): T {
        return mApplicationProvider[modelClass]
    }
}
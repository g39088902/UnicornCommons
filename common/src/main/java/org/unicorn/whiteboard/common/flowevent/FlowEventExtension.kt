package org.unicorn.whiteboard.common.flowevent

import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * create by tlq,on 2022/3/1
 * Desc:
 */
//_______________________________________
//          post event
//_______________________________________

//Application范围的事件
inline fun <reified T> postEvent(event: T, timeMillis: Long = 0L) {
    ApplicationScopeViewModelProvider.getApplicationProvider(FlowEventCoreViewModel::class.java)
        .postEvent(T::class.java.name, event!!, timeMillis)
}

//限定范围的事件
inline fun <reified T> postEvent(scope: ViewModelStoreOwner, event: T, timeMillis: Long = 0L) {
    ViewModelProvider(scope)[FlowEventCoreViewModel::class.java]
        .postEvent(T::class.java.name, event!!, timeMillis)
}


//_______________________________________
//          deal event
//_______________________________________

/**
 * 获取观察事件数
 */
inline fun <reified T> getEventObserverCount(event: Class<T>): Int {
    return ApplicationScopeViewModelProvider.getApplicationProvider(FlowEventCoreViewModel::class.java)
        .getEventObserverCount(event.name)
}
/**
 * 获取观察事件数
 */
inline fun <reified T> getEventObserverCount(scope: ViewModelStoreOwner, event: Class<T>): Int {
    return ViewModelProvider(scope)[FlowEventCoreViewModel::class.java]
        .getEventObserverCount(event.name)
}


/**
 * 移除粘性事件
 */
inline fun <reified T> removeStickyEvent(event: Class<T>) {
    ApplicationScopeViewModelProvider.getApplicationProvider(FlowEventCoreViewModel::class.java)
        .removeStickEvent(event.name)
}

/**
 * 清除粘性事件
 * ViewModelStoreOwner
 */
inline fun <reified T> removeStickyEvent(scope: ViewModelStoreOwner, event: Class<T>) {
    ViewModelProvider(scope)[FlowEventCoreViewModel::class.java]
        .removeStickEvent(event.name)
}


/**
 * 清除事件缓存
 */
inline fun <reified T> clearStickyEvent(event: Class<T>) {
    ApplicationScopeViewModelProvider.getApplicationProvider(FlowEventCoreViewModel::class.java)
        .clearStickEvent(event.name)
}
/**
 * 清除事件缓存
 */
inline fun <reified T> clearStickyEvent(scope: ViewModelStoreOwner, event: Class<T>) {
    ViewModelProvider(scope)[FlowEventCoreViewModel::class.java]
        .clearStickEvent(event.name)
}

/**
 * 获取观察事件数
 */
fun <T> LifecycleOwner.launchWhenStateAtLeast(
    minState: Lifecycle.State,
    block: suspend CoroutineScope.() -> T
): Job {
    return lifecycleScope.launch {
        lifecycle.whenStateAtLeast(minState, block)
    }
}
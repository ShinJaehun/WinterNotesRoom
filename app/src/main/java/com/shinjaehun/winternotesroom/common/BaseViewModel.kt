package com.shinjaehun.winternotesroom.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel<T>(
    private val uiContext: CoroutineContext
): ViewModel(), CoroutineScope {

    abstract fun handleEvent(event: T)

    protected var jobTracker: Job = Job()

    protected val errorState = MutableLiveData<String>()
    val error: LiveData<String> get() = errorState

    protected val loadingState = MutableLiveData<Unit>()
    val loading: LiveData<Unit> get() = loadingState

    override val coroutineContext: CoroutineContext
        get() = uiContext + jobTracker
}
package com.fvink.mobilebanking.ui.base


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<State : ViewState, Event : ViewEvent>(initialState: State) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        _isLoading.value
    )

    private val _events = Channel<Event>(Channel.BUFFERED)

    /**
     * Represents one-time events which should be received only once.
     */
    val events = _events.receiveAsFlow()

    private val _state = MutableStateFlow(initialState)

    /**
     * Represents the view state.
     */
    val state: StateFlow<State> = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        _state.value
    )

    protected fun updateState(block: (State) -> State) {
        _state.update(block)
    }

    protected fun emitEvent(event: Event) {
        _events.trySend(event)
    }

    protected fun showLoading() {
        _isLoading.value = true
    }

    protected fun hideLoading() {
        _isLoading.value = false
    }

    /**
     * Launches a coroutine using the viewModelScope
     */
    protected fun launch(coroutine: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(block = coroutine)
    }
}
package com.hamza.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class AddNumberViewModel : ViewModel() {
    //To send intent from activity TO VIEW model
    val channel = Channel<MainIntent>(Channel.UNLIMITED)

    private val _stateFlow = MutableStateFlow<MainViewState>(MainViewState.Idle)
    val stateFlow get() = _stateFlow
    private var number = 0

    init {
        processIntent()
    }

    //process
    private fun processIntent() {
        viewModelScope.launch {
            channel.consumeAsFlow().collect {
                when (it) {
                    is MainIntent.AddNumber -> reduceResult()
                }
            }
        }
    }

    //reduce
    private fun reduceResult() {
        viewModelScope.launch {
            _stateFlow.value =
                try {
                    MainViewState.Result(++number  )
                } catch (e: Exception) {
                    MainViewState.Error(e.message!!)
                }
        }

    }


}
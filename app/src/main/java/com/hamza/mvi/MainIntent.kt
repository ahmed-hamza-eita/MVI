package com.hamza.mvi

sealed class MainIntent {

    //What user doing?
    object AddNumber : MainIntent()
}

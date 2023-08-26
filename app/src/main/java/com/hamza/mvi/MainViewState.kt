package com.hamza.mvi

open class MainViewState {
    //What is  show for user in View ??

    //idle
    object Idle : MainViewState()

    //Result
    data class Result(val number: Int) : MainViewState()

    //Error
    data class Error(val message: String) : MainViewState()
}
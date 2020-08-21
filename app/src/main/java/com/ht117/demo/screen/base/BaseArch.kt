package com.ht117.demo.screen.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface IIntent

interface IState

interface IModel<State: IState, Intent: IIntent> {
    val intents: Channel<Intent>
    val state: LiveData<State>
}

interface IView<State: IState> {
    fun render(state: State)
}

suspend fun<T: IState> updateState(newState: MutableLiveData<T>, publicState: LiveData<T>, handler: suspend(state: T) -> T) {
    newState.postValue(handler(publicState.value!!))
}
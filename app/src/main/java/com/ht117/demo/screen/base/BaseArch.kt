package com.ht117.demo.screen.base

import androidx.lifecycle.LiveData
import kotlinx.coroutines.channels.Channel

interface IIntent

interface IState

interface IModel<State: IState, Intent: IIntent> {
    val intents: Channel<Intent>
    val state: LiveData<State>
}

interface IView<State: IState> {
    fun render(state: State)
}
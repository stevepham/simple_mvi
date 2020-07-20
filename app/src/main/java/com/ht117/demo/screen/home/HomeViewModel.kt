package com.ht117.demo.screen.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ht117.demo.screen.base.IModel
import com.ht117.domain.usecase.GetUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val getUsers: GetUser): ViewModel(), IModel<HomeState, HomeIntent> {

    override val intents: Channel<HomeIntent> = Channel(Channel.UNLIMITED)
    override val state: LiveData<HomeState> get() = _state

    private val _state = MutableLiveData<HomeState>().apply { value = HomeState() }

    init {
        handleIntent()
    }

    private fun handleIntent() = viewModelScope.launch {
        intents.consumeAsFlow().collect {
            when (it) {
                HomeIntent.FetchUser -> fetchUsers()
                HomeIntent.RefreshUser -> fetchUsers()
            }
        }
    }

    private fun fetchUsers() = viewModelScope.launch(Dispatchers.IO) {
        try {
            updateState { it.copy(isLoading = true) }
            updateState { it.copy(isLoading = false, users = getUsers.invoke()) }
        } catch (exp: Exception) {
            updateState { it.copy(isLoading = false, message = exp.message) }
        }
    }

    private suspend fun updateState(handler: suspend(intent: HomeState)-> HomeState) {
        _state.postValue(handler(state.value!!))
    }
}
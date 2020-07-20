package com.ht117.data.source.api

import com.ht117.domain.User
import kotlinx.coroutines.delay

class UserApi {
    suspend fun getUsers(): List<User> {
        delay(2_500)
        return listOf(
            User(name = "Edwin", position = "GK"),
            User(name = "Evra", position = "LB"),
            User(name = "Vidic", position = "CB"),
            User(name = "Ferdinand", position = "CB"),
            User(name = "Brown", position = "RB"),
            User(name = "Giggs", position = "LM"),
            User(name = "Scholes", position = "CM"),
            User(name = "Hargreaves", position = "CM"),
            User(name = "Ronaldo", position = "RM"),
            User(name = "Rooney", position = "CF"),
            User(name = "Berbatov", position = "CF")
        )
    }
}
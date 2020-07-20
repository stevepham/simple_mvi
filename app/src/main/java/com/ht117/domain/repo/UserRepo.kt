package com.ht117.domain.repo

import com.ht117.domain.User

interface IUserRepo {
    suspend fun getUserList(): List<User>
}
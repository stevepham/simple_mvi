package com.ht117.data.repo

import com.ht117.data.source.api.UserApi
import com.ht117.domain.User
import com.ht117.domain.repo.IUserRepo

class UserRepoImpl(private val userApi: UserApi): IUserRepo {

    override suspend fun getUserList(): List<User> {
        return userApi.getUsers()
    }
}
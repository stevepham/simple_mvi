package com.ht117.domain.usecase

import com.ht117.domain.User
import com.ht117.domain.repo.IUserRepo

class GetUser(private val userRepo: IUserRepo) {

    suspend fun invoke(): List<User> {
        return userRepo.getUserList()
    }

}
package com.ht117.data

import com.ht117.data.repo.UserRepoImpl
import com.ht117.data.source.api.UserApi
import com.ht117.domain.repo.IUserRepo
import org.koin.dsl.module

val dataModule = module {
    single { UserApi() }
    single<IUserRepo> { UserRepoImpl(get()) }
}
package com.ht117.domain

import com.ht117.domain.usecase.GetUser
import org.koin.dsl.module

val domainModule = module {
    single { GetUser(get()) }
}
package com.herehs.worldecopy.di

import com.herehs.worldecopy.domain.usecase.GetLeaderboardUseCase
import com.herehs.worldecopy.domain.usecase.SignInUseCase
import com.herehs.worldecopy.domain.usecase.SignUpUseCase
import org.koin.dsl.module

val domainModule = module {
    single { SignUpUseCase(get()) }
    single { SignInUseCase(get()) }
    single { GetLeaderboardUseCase(get()) }
}
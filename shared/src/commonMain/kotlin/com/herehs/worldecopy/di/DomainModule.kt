package com.herehs.worldecopy.di

import com.herehs.worldecopy.domain.usecase.FinishGameUseCase
import com.herehs.worldecopy.domain.usecase.GetLeaderboardUseCase
import com.herehs.worldecopy.domain.usecase.SignInUseCase
import com.herehs.worldecopy.domain.usecase.SignUpUseCase
import com.herehs.worldecopy.domain.usecase.StartGameUseCase
import org.koin.dsl.module

val domainModule = module {
    single { SignUpUseCase(get()) }
    single { SignInUseCase(get()) }
    single { GetLeaderboardUseCase(get()) }
    single { StartGameUseCase(get()) }
    single { FinishGameUseCase(get()) }
}
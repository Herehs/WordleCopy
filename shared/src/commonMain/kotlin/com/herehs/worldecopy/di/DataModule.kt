package com.herehs.worldecopy.di

import com.herehs.worldecopy.data.local.token_storage.TokenStorage
import com.herehs.worldecopy.data.local.token_storage.TokenStorageImpl
import com.herehs.worldecopy.data.remote.auth.AuthApiService
import com.herehs.worldecopy.data.remote.auth.AuthApiServiceImpl
import com.herehs.worldecopy.data.remote.game.GameApiService
import com.herehs.worldecopy.data.remote.game.GameApiServiceImpl
import com.herehs.worldecopy.data.repository.AuthRepositoryImpl
import com.herehs.worldecopy.data.repository.GameRepositoryImpl
import com.herehs.worldecopy.data.repository.TokenRepositoryImpl
import com.herehs.worldecopy.data.util.createApiClient
import com.herehs.worldecopy.data.util.createRefreshClient
import com.herehs.worldecopy.domain.repository.AuthRepository
import com.herehs.worldecopy.domain.repository.GameRepository
import com.herehs.worldecopy.domain.repository.TokenRepository
import io.ktor.client.HttpClient
import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    single<TokenStorage> {
        TokenStorageImpl(get())
    }

    single<HttpClient>(named(HttpClientType.REFRESH)) {
        createRefreshClient()
    }

    single<HttpClient>(named(HttpClientType.MAIN)) {
        createApiClient(
            refreshClient = get(named(HttpClientType.REFRESH)),
            storage = get()
        )
    }
    
    single<AuthApiService> {
        AuthApiServiceImpl(
            client = get(named(HttpClientType.REFRESH))
        )
    }

    single<GameApiService> {
        GameApiServiceImpl(
            client = get(named(HttpClientType.MAIN))
        )
    }
    
    single<AuthRepository> {
        AuthRepositoryImpl(
            api = get(),
            tokenStorage = get(),
        )
    }

    single<GameRepository> {
        GameRepositoryImpl(get())
    }

    single<TokenRepository> {
        TokenRepositoryImpl(get())
    }
}

enum class HttpClientType {
    MAIN,
    REFRESH
}
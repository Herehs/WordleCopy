package com.herehs.worldecopy.data.util

import com.herehs.worldecopy.core.common.BASE_URL
import com.herehs.worldecopy.data.local.token_storage.TokenStorage
import com.herehs.worldecopy.data.local.token_storage.dto.WebToken
import com.herehs.worldecopy.data.util.dto.RefreshRequest
import com.herehs.worldecopy.data.util.dto.RefreshTokenDto
import com.herehs.worldecopy.data.util.dto.toWebToken
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.serialization.json.Json




fun HttpClientConfig<*>.commonConfig() {
    install(ContentNegotiation) {
        json(
            Json {
                ignoreUnknownKeys = true
            }
        )
    }
    install(HttpTimeout) {
        requestTimeoutMillis = 15_000
        connectTimeoutMillis = 10_000
        socketTimeoutMillis = 15_000
    }
    defaultRequest {
        url(BASE_URL)
        contentType(ContentType.Application.Json)
    }
}

fun createRefreshClient() = HttpClient { commonConfig() }

fun createApiClient(
    refreshClient: HttpClient,
    storage: TokenStorage
) = HttpClient {
    commonConfig()

    install(Auth){
        bearer {
            loadTokens {
                storage.get().firstOrNull()?.let {
                    BearerTokens(
                        accessToken = it.access,
                        refreshToken = it.refresh
                    )
                }
            }
            refreshTokens {
                val old = storage.get().firstOrNull() ?: return@refreshTokens null

                val response = refreshClient.post("/api/players/refresh") {
                    setBody(RefreshRequest(old.refresh))
                }

                if(!response.status.isSuccess()) {
                    storage.clear()
                    return@refreshTokens null
                }

                val dto = response.body<RefreshTokenDto>().toWebToken()

                storage.save(token = dto)
                BearerTokens(
                    accessToken = dto.access,
                    refreshToken = dto.refresh
                )
            }

            sendWithoutRequest { request ->
                request.url.host == "tiktok-lgbt-ray.space"
            }
        }
    }
}
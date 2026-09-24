package com.herehs.worldecopy.data.remote.auth

import com.herehs.worldecopy.data.remote.auth.dto.LoginRequestDto
import com.herehs.worldecopy.data.remote.auth.dto.LoginResponseDto
import com.herehs.worldecopy.data.remote.auth.dto.RegisterRequestDto
import com.herehs.worldecopy.data.remote.auth.dto.RegisterResponseDto
import com.herehs.worldecopy.data.remote.auth.dto.UnprocessableEntityDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.http.isSuccess
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

class AuthApiServiceImpl(
    private val client: HttpClient
) : AuthApiService {
    override suspend fun register(
        username: String,
        password: String
    ): RegisterResponseDto {
        val response = client.post("/api/players/register") {
            setBody(
                LoginRequestDto(
                    username = username,
                    password = password
                )
            )
        }
        response.throwIfError()

        return response.body<RegisterResponseDto>()
    }

    override suspend fun login(
        username: String,
        password: String
    ): LoginResponseDto {
        return client.post("/api/players/login") {
            setBody(
                RegisterRequestDto(
                    username = username,
                    password = password
                )
            )
        }.body()
    }

    private suspend fun HttpResponse.throwIfError() {
        if (status.isSuccess()) return

        val root = runCatching { Json.parseToJsonElement(bodyAsText()).jsonObject }.getOrNull()
        val detail = root?.get("detail")

        val message = when {
            status == HttpStatusCode.UnprocessableEntity && detail is JsonArray -> {
                val first = detail.firstOrNull()?.jsonObject
                val field = first?.get("loc")?.jsonArray?.lastOrNull()?.jsonPrimitive?.content
                val msg = first?.get("msg")?.jsonPrimitive?.content

                when (field) {
                    "username" -> "Некорректный никнейм"
                    "password" -> "Некорректный пароль"
                    else -> msg ?: "Проверьте введённые данные"
                }
            }
            detail is JsonPrimitive -> detail.content
            else -> "Ошибка сервера (${status.value})"
        }

        throw IllegalStateException(message)
    }
}
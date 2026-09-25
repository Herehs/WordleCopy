package com.herehs.worldecopy.data.util

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

suspend fun HttpResponse.throwIfError() {
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

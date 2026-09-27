package com.herehs.worldecopy.data.remote.game.mapper

import com.herehs.worldecopy.data.remote.game.dto.StartGameResponseDto
import com.herehs.worldecopy.domain.model.GameData

fun StartGameResponseDto.toDomain() = GameData(
    id = this.id,
    answer = this.answer,
    wordLength = this.wordLength,
    maxAttempts = this.maxAttempts
)
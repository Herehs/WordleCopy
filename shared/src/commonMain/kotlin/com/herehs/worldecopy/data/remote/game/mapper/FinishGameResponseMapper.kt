package com.herehs.worldecopy.data.remote.game.mapper

import com.herehs.worldecopy.data.remote.game.dto.FinishGameResponseDto
import com.herehs.worldecopy.domain.model.GameResult

fun FinishGameResponseDto.toDomain() = GameResult(
    score = score
)
package com.herehs.worldecopy.data.remote.game.mapper

import com.herehs.worldecopy.data.remote.game.dto.FinishGameRequestDto
import com.herehs.worldecopy.domain.model.FinishGameData

fun FinishGameData.toData() = FinishGameRequestDto(
    won = won,
    attemptsUsed = attemptsUsed
)
package com.eritlab.jexmon.domain.repo

import com.eritlab.jexmon.domain.item.GameDetailItem
import com.eritlab.jexmon.domain.item.toGameDetail
import com.eritlab.jexmon.domain.service.GameService
import javax.inject.Inject

class GameDetailRepository @Inject constructor(private val gameService: GameService) {
    suspend fun getDetail(id: Int): GameDetailItem {
        val response = gameService.getDetailGame(id)
        return response.toGameDetail()
    }
}
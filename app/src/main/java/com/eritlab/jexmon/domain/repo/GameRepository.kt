package com.eritlab.jexmon.domain.repo

import com.eritlab.jexmon.domain.item.GameItem
import com.eritlab.jexmon.domain.item.toGameItem
import com.eritlab.jexmon.domain.service.GameService
import javax.inject.Inject

class GameRepository @Inject constructor(private val gameService: GameService) {
    suspend fun getGames():List<GameItem>{
        return gameService.getGames().map {
            it.toGameItem()
        }
    }
    suspend fun getCategoryGame(category:String):List<GameItem>{
        return gameService.getCategoryGame(category).map{
            it.toGameItem()
        }
    }
    suspend fun getFilterGame(platform:String, category:String, sortBy:String):List<GameItem>{
        return gameService.getFilterGame(platform, category, sortBy).map {
            it.toGameItem()
        }
    }
}
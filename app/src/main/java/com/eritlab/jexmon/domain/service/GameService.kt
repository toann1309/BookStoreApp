package com.eritlab.jexmon.domain.service

import com.eritlab.jexmon.domain.model.gameDetailModel.GameDetailModel
import com.eritlab.jexmon.domain.model.gameModel.GamesModel
import com.eritlab.jexmon.domain.repository.GameAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GameService @Inject constructor(private val gameApi:GameAPI) {
    suspend fun getGames():List<GamesModel>{
        return withContext(Dispatchers.IO){
            val games = gameApi.getGame()
            games.body()?: emptyList()
        }
    }
    suspend fun getDetailGame(id:Int):GameDetailModel{
        return gameApi.getDetailGame(id)
    }
}
package com.eritlab.jexmon.domain.repository
import com.eritlab.jexmon.domain.model.gameDetailModel.GameDetailModel
import com.eritlab.jexmon.domain.model.gameModel.GamesModel
import com.eritlab.jexmon.utils.Constants.Companion.GAMES_ENDPOINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GameAPI {
    @GET(GAMES_ENDPOINT)
    suspend fun getGame():Response<List<GamesModel>>
    @GET("game")
    suspend fun getDetailGame(@Query("id") id:Int):GameDetailModel
}
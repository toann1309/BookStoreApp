package com.eritlab.jexmon.domain.item

import com.eritlab.jexmon.domain.model.gameDetailModel.GameDetailModel
import com.eritlab.jexmon.domain.model.gameDetailModel.MinimumSystemRequirements
import com.eritlab.jexmon.domain.model.gameDetailModel.Screenshot

data class GameDetailItem(
    val description: String,
    val developer: String,
    val freetogame_profile_url: String,
    val game_url: String,
    val genre: String,
    val id: Int,
    val minimum_system_requirements: MinimumSystemRequirements,
    val platform: String,
    val publisher: String,
    val release_date: String,
    val screenshots: List<Screenshot>,
    val short_description: String,
    val status: String,
    val thumbnail: String,
    val title: String
)
fun GameDetailModel.toGameDetail() = GameDetailItem(description, developer, freetogame_profile_url, game_url, genre, id,minimum_system_requirements, platform, publisher, release_date, screenshots, short_description, status, thumbnail, title)

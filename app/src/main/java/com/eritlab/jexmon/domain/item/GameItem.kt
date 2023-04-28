package com.eritlab.jexmon.domain.item

import com.eritlab.jexmon.domain.model.gameModel.GamesModel

data class GameItem(
    val id: Int,
    val title: String,
    val thumbnail: String,
    val short_description: String,
)
fun GamesModel.toGameItem() = GameItem(id, title, thumbnail, short_description)
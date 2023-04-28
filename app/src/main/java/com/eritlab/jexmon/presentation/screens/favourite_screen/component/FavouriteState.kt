package com.eritlab.jexmon.presentation.screens.favourite_screen.component

import com.eritlab.jexmon.domain.model.gameModel.GamesModel

data class FavouriteState(
    val isLoading:Boolean=false,
    val productDetail: GamesModel?=null,
    val errorMessage:String=""
)

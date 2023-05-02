package com.eritlab.jexmon.presentation.graphs.filter_graph

sealed class FilterGameScreen(val route:String){
    object FilterScreen:FilterGameScreen("filter_screen")
}

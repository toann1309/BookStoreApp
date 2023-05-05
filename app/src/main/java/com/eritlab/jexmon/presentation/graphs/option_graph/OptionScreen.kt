package com.eritlab.jexmon.presentation.graphs.option_graph

sealed class OptionScreen(val route:String){
    object SearchScreen:OptionScreen("search_screen")
}

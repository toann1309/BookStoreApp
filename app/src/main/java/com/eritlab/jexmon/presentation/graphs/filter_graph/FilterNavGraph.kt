package com.eritlab.jexmon.presentation.graphs.filter_graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.eritlab.jexmon.common.Constrains
import com.eritlab.jexmon.presentation.graphs.Graph
import com.eritlab.jexmon.presentation.graphs.detail_graph.DetailScreen
import com.eritlab.jexmon.presentation.graphs.option_graph.OptionScreen
import com.eritlab.jexmon.presentation.screens.filter_screen.component.component.FilterScreen
import com.eritlab.jexmon.presentation.screens.search_screen.component.component.SearchScreen

fun NavGraphBuilder.filterScreen(navController: NavController){
    navigation(
        route = Graph.FILTERGAME,
        startDestination = FilterGameScreen.FilterScreen.route + "/{${Constrains.PRICE}}/{${Constrains.PUBLISHER}}"
    ){
        composable(FilterGameScreen.FilterScreen.route+"/{price}/{publisher}"){
            FilterScreen(){productId ->
                navController.navigate(DetailScreen.ProductDetailScreen.route + "/${productId}")
            }
        }
    }
}
package com.eritlab.jexmon.presentation.graphs.option_graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.eritlab.jexmon.common.Constrains
import com.eritlab.jexmon.presentation.graphs.Graph
import com.eritlab.jexmon.presentation.graphs.detail_graph.DetailScreen
import com.eritlab.jexmon.presentation.screens.search_screen.component.component.SearchScreen

fun NavGraphBuilder.optionScreen(navController: NavController){
    navigation(
        route = Graph.OPTIONS,
        startDestination = OptionScreen.SearchScreen.route+"/{${Constrains.SEARCH_BOOK}}"
    ){
        composable(OptionScreen.SearchScreen.route+"/{keyword}"){
            SearchScreen(){productId ->
                navController.navigate(DetailScreen.ProductDetailScreen.route + "/${productId}")
            }
        }
    }
}
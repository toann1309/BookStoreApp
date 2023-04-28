package com.eritlab.jexmon.presentation.graphs.detail_graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.eritlab.jexmon.common.Constrains
import com.eritlab.jexmon.presentation.graphs.Graph
import com.eritlab.jexmon.presentation.screens.cart_screen.component.CartScreen
import com.eritlab.jexmon.presentation.screens.check_out_screen.component.CheckOut
import com.eritlab.jexmon.presentation.screens.notification_screen.component.NotificationScreen
import com.eritlab.jexmon.presentation.screens.product_detail_screen.GameDetailViewModel
import com.eritlab.jexmon.presentation.screens.product_detail_screen.component.ProductDetailScreen


fun NavGraphBuilder.detailNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.DETAILS,
//        startDestination = DetailScreen.ProductDetailScreen.route + "/{${Constrains.PRODUCT_ID_PARAM}}"
        startDestination = DetailScreen.ProductDetailScreen.route+"/{${Constrains.PRODUCT_ID_PARAM}}"
    ) {
        composable(DetailScreen.CartScreen.route) {
            CartScreen(navController = navController) {
                navController.popBackStack()
            }
        }
        composable(DetailScreen.NotificationScreen.route) {
            NotificationScreen()
        }
        composable(DetailScreen.ProductDetailScreen.route + "/{productId}") {
            ProductDetailScreen() {
                navController.popBackStack()
            }
        }
        composable(DetailScreen.CheckOut.route){
            CheckOut()
        }
    }
}
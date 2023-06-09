package com.eritlab.jexmon.presentation.graphs.detail_graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.eritlab.jexmon.common.Constrains
import com.eritlab.jexmon.presentation.graphs.Graph
import com.eritlab.jexmon.presentation.screens.cart_screen.component.CartScreen
import com.eritlab.jexmon.presentation.screens.checkout_screen.component.CheckOut
import com.eritlab.jexmon.presentation.screens.checkout_screen.component.component.CheckoutBuyNow
import com.eritlab.jexmon.presentation.screens.detail_checkout_screen.component.DetailCheckoutBuyNow
import com.eritlab.jexmon.presentation.screens.detail_checkout_screen.component.DetailsCheckOut
import com.eritlab.jexmon.presentation.screens.notification_screen.component.NotificationScreen
import com.eritlab.jexmon.presentation.screens.product_detail_screen.component.ProductDetailScreen


fun NavGraphBuilder.detailNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.DETAILS,
//        startDestination = DetailScreen.ProductDetailScreen.route + "/{${Constrains.PRODUCT_ID_PARAM}}"
        startDestination = DetailScreen.ProductDetailScreen.route+"/{${Constrains.PRODUCT_ID_PARAM}}"
    ) {
        composable(DetailScreen.CartScreen.route) {
            CartScreen(navController=navController)
        }
        composable(DetailScreen.NotificationScreen.route) {
            NotificationScreen()
        }
        composable(DetailScreen.ProductDetailScreen.route + "/{productId}") {
            ProductDetailScreen(navController = navController)
        }
        composable(DetailScreen.CheckOut.route){
            CheckOut(navController = navController)
        }
        composable(DetailScreen.CheckoutBuyNow.route){
            CheckoutBuyNow(navController = navController)
        }
        composable(DetailScreen.DetailCheckOut.route){
            DetailsCheckOut(navController = navController)
        }
        composable(DetailScreen.DetailCheckoutBuyNow.route){
            DetailCheckoutBuyNow(navController = navController)
        }
    }
}



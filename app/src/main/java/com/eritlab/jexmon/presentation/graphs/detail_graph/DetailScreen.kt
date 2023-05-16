package com.eritlab.jexmon.presentation.graphs.detail_graph

sealed class DetailScreen( val route: String) {
    object CartScreen : DetailScreen("cart_screen")
    object NotificationScreen : DetailScreen("notification_screen")
    object ProductDetailScreen : DetailScreen("product_detail_screen")
    object CheckOut : DetailScreen("checkout_screen")
    object DetailCheckOut : DetailScreen("detail_checkout_screen")
    object CheckoutBuyNow : DetailScreen("checkout_buynow_screen")
    object DetailCheckoutBuyNow:DetailScreen("detail_checkout_buy_now_screen")
}
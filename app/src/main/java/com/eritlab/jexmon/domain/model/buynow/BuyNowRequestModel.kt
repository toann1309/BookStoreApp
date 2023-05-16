package com.eritlab.jexmon.domain.model.buynow

data class BuyNowRequestModel(
    val address: String,
    val itemDetail: ItemDetail,
    val paymentMethod: String,
    val phone: String,
    val totalPrice: Int,
    val userId: Int,
    val userName: String
)
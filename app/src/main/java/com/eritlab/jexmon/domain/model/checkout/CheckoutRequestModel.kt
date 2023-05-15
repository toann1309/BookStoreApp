package com.eritlab.jexmon.domain.model.checkout

import com.eritlab.jexmon.domain.model.getCart.Item

data class CheckoutRequestModel(
    val address: String,
    val itemDTOS: List<Item>,
    val paymentMethod: String,
    val phone: String,
    val totalPrice: Int,
    val userId: Int,
    val username: String
)
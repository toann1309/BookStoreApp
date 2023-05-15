package com.eritlab.jexmon.domain.item

import com.eritlab.jexmon.domain.model.checkout.CheckoutRequestModel
import com.eritlab.jexmon.domain.model.checkout.ItemDTOS
import com.eritlab.jexmon.domain.model.getCart.Item

data class CheckoutRequestItem(
    val address: String,
    val itemDTOS: List<Item>,
    val paymentMethod: String,
    val phone: String,
    val totalPrice: Int,
    val userId: Int,
    val username: String
)
fun CheckoutRequestModel.toGetCheckoutRequest() = CheckoutRequestItem(address, itemDTOS, paymentMethod, phone, totalPrice, userId, username)
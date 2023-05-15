package com.eritlab.jexmon.domain.item

import com.eritlab.jexmon.domain.model.checkout.CheckoutResponseModel

data class CheckoutResponseItem(
    val message: String,
    val success: Boolean
)
fun CheckoutResponseModel.toGetCheckoutResponse() = CheckoutResponseItem(message, success)

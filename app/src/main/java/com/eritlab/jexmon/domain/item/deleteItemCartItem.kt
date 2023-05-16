package com.eritlab.jexmon.domain.item

import com.eritlab.jexmon.domain.model.deleteItemCartModel.deleteItemCardModel

data class deleteItemCartItem(
    val message: String,
    val success: Boolean
)
fun deleteItemCardModel.toGetDeleteItemCart() = deleteItemCartItem(message, success)
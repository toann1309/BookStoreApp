package com.eritlab.jexmon.domain.model.getCart

data class Item(
    val cartId: Int,
    val id: Int,
    val itemName: String,
    val price: Double,
    val productId: Int,
    var quantity: Int,
    val thumbnailPath: String
)
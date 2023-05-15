package com.eritlab.jexmon.domain.model.checkout

data class ItemDTOS(
    val cartId: Int,
    val id: Int,
    val itemName: String,
    val price: Double,
    val productId: Int,
    val quantity: Int,
    val thumbnailPath: String
)
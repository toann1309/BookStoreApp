package com.eritlab.jexmon.domain.model.buynow

data class ItemDetail(
    val itemName: String?,
    val price: Double,
    val productId: Int,
    var quantity: Int,
    val thumbnailPath: String?
)
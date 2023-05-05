package com.eritlab.jexmon.domain.model.bookDetailModel

data class BookDetailModel(
    val description: String,
    val id: Int,
    val imagesProduct: List<String>,
    val price: Double,
    val productName: String,
    val publisher: String,
    val quantity: Int
)
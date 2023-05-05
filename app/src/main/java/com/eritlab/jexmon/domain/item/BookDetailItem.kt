package com.eritlab.jexmon.domain.item

import com.eritlab.jexmon.domain.model.bookDetailModel.BookDetailModel

data class BookDetailItem(
    val description: String,
    val id: Int,
    val imagesProduct: List<String>,
    val price: Double,
    val productName: String,
    val publisher: String,
    val quantity: Int
)
fun BookDetailModel.toBookDetailItem() = BookDetailItem(description, id, imagesProduct, price, productName, publisher, quantity)

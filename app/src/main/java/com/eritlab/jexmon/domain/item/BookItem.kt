package com.eritlab.jexmon.domain.item

import com.eritlab.jexmon.domain.model.bookModel.BookModel

data class BookItem(
    val id: Int,
    val imagePath: String,
    val price: Double,
    val productName: String
)
fun BookModel.toBookItem() = BookItem(id, imagePath, price, productName)
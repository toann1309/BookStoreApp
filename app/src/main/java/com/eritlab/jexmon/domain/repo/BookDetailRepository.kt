package com.eritlab.jexmon.domain.repo

import com.eritlab.jexmon.domain.item.BookDetailItem
import com.eritlab.jexmon.domain.item.toBookDetailItem
import com.eritlab.jexmon.domain.service.BookService
import javax.inject.Inject

class BookDetailRepository @Inject constructor(private val bookService: BookService) {
    suspend fun getBookDetail(id:Int):BookDetailItem{
        val response = bookService.getBookDetail(id)
        return response.toBookDetailItem()
    }
}
package com.eritlab.jexmon.domain.repo

import com.eritlab.jexmon.domain.item.BookItem
import com.eritlab.jexmon.domain.item.toBookItem
import com.eritlab.jexmon.domain.service.BookService
import javax.inject.Inject

class BookRepository @Inject constructor(private val bookService: BookService) {
    suspend fun getBooks():List<BookItem>{
        return bookService.getBooks().map {
            it.toBookItem()
        }
    }
    suspend fun getSearchBook(keyword:String):List<BookItem>{
        return bookService.getSearchBooks(keyword).map {
            it.toBookItem()
        }
    }
    suspend fun getFilterBook(price:String, publisher:String):List<BookItem>{
        return bookService.getFilterBooks(price,publisher).map {
            it.toBookItem()
        }
    }
}
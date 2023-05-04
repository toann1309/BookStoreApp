package com.eritlab.jexmon.domain.service

import com.eritlab.jexmon.domain.model.bookDetailModel.BookDetailModel
import com.eritlab.jexmon.domain.model.bookModel.BookModel
import com.eritlab.jexmon.domain.repository.BookStoreAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BookService @Inject constructor(private val bookStoreAPI: BookStoreAPI){
    suspend fun getBooks():List<BookModel>{
        return withContext(Dispatchers.IO){
            val books = bookStoreAPI.getBook()
            books.body()?: emptyList()
        }
    }
    suspend fun getBookDetail(id:Int): BookDetailModel {
        return bookStoreAPI.getDetailBook(id)
    }
    suspend fun getSearchBooks(keyword:String):List<BookModel>{
        return withContext(Dispatchers.IO){
            val booksSearch = bookStoreAPI.searchBook(keyword)
            booksSearch.body()?: emptyList()
        }
    }
    suspend fun getFilterBooks(price:String, publisher:String):List<BookModel>{
        return withContext(Dispatchers.IO){
            val booksFilter = bookStoreAPI.filterBook(price,publisher)
            booksFilter.body()?: emptyList()
        }
    }
}
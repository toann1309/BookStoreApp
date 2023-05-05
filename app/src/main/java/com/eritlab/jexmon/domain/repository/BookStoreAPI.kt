package com.eritlab.jexmon.domain.repository

import com.eritlab.jexmon.domain.model.bookDetailModel.BookDetailModel
import com.eritlab.jexmon.domain.model.bookModel.BookModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BookStoreAPI {
    @GET("all")
    suspend fun getBook(): Response<List<BookModel>>
    @GET("{id}")
    suspend fun getDetailBook(@Path("id") id:Int):BookDetailModel
    @GET("search")
    suspend fun searchBook(@Query("keyword") keyword:String):Response<List<BookModel>>
    @GET("filter")
    suspend fun filterBook(@Query("price") price:String, @Query("publisher") publisher:String):Response<List<BookModel>>
}
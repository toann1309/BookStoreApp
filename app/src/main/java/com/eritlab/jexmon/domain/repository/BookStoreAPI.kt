package com.eritlab.jexmon.domain.repository

import com.eritlab.jexmon.domain.item.AddCartRequestItem
import com.eritlab.jexmon.domain.item.CheckoutRequestItem
import com.eritlab.jexmon.domain.model.addcart.AddCartResponseModel
import com.eritlab.jexmon.domain.model.bookDetailModel.BookDetailModel
import com.eritlab.jexmon.domain.model.bookModel.BookModel
import com.eritlab.jexmon.domain.model.checkout.CheckoutResponseModel
import com.eritlab.jexmon.domain.model.getCart.GetCartModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface BookStoreAPI {
    @GET("product/all")
    suspend fun getBook(): Response<List<BookModel>>
    @GET("product/{id}")
    suspend fun getDetailBook(@Path("id") id:Int):BookDetailModel
    @GET("product/search")
    suspend fun searchBook(@Query("keyword") keyword:String):Response<List<BookModel>>
    @GET("product/filter")
    suspend fun filterBook(@Query("price") price:String, @Query("publisher") publisher:String):Response<List<BookModel>>
    @GET("product/popular")
    suspend fun getPopularBook():Response<List<BookModel>>
    @POST("cart")
    suspend fun addCart(@Body addCart:AddCartRequestItem):AddCartResponseModel
    @GET("cart/items")
    suspend fun getCart(@Query("userId") userId:Int):GetCartModel
    @POST("cart/checkout")
    suspend fun checkout(@Body checkoutRequestItem: CheckoutRequestItem):CheckoutResponseModel
}
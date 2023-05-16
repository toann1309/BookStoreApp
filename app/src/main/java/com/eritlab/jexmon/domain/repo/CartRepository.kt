package com.eritlab.jexmon.domain.repo

import com.eritlab.jexmon.domain.item.*
import com.eritlab.jexmon.domain.service.BookService
import javax.inject.Inject

class CartRepository @Inject constructor(private val bookService: BookService) {
    suspend fun addCart(addCartRequestItem: AddCartRequestItem):AddCartResponseItem{
        val response = bookService.addCart(addCartRequestItem)
        return response.toGetAddCartResponse()
    }
    suspend fun getCart(userId:Int):GetCartItem{
        val response = bookService.getCart(userId)
        return response.toGetCart()
    }
    suspend fun chẹckout(checkoutRequestItem: CheckoutRequestItem):CheckoutResponseItem{
        val response = bookService.checkout(checkoutRequestItem)
        return response.toGetCheckoutResponse()
    }
    suspend fun buyNow(buyNowRequestItem: BuyNowRequestItem):BuyNowResponseItem{
        val response = bookService.buyNow(buyNowRequestItem)
        return response.toGetBuyNowResponse()
    }
    suspend fun deleteCart(cartId:Int):deleteItemCartItem{
        val response = bookService.deleteCart(cartId)
        return response.toGetDeleteItemCart()
    }
}
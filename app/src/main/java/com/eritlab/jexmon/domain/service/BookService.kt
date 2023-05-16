package com.eritlab.jexmon.domain.service

import com.eritlab.jexmon.domain.item.AddCartRequestItem
import com.eritlab.jexmon.domain.item.BuyNowRequestItem
import com.eritlab.jexmon.domain.item.CheckoutRequestItem
import com.eritlab.jexmon.domain.model.addcart.AddCartResponseModel
import com.eritlab.jexmon.domain.model.bookDetailModel.BookDetailModel
import com.eritlab.jexmon.domain.model.bookModel.BookModel
import com.eritlab.jexmon.domain.model.buynow.BuyNowResponseModel
import com.eritlab.jexmon.domain.model.checkout.CheckoutRequestModel
import com.eritlab.jexmon.domain.model.checkout.CheckoutResponseModel
import com.eritlab.jexmon.domain.model.deleteItemCartModel.deleteItemCardModel
import com.eritlab.jexmon.domain.model.getCart.GetCartModel
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
    suspend fun getPopuplarBook():List<BookModel>{
        return withContext(Dispatchers.IO){
            val getPopularBook = bookStoreAPI.getPopularBook();
            getPopularBook.body()?: emptyList()
        }
    }
    suspend fun addCart(addCartRequestItem: AddCartRequestItem):AddCartResponseModel{
        return bookStoreAPI.addCart(addCartRequestItem)
    }
    suspend fun getCart(userId:Int):GetCartModel{
        return bookStoreAPI.getCart(userId)
    }
    suspend fun checkout(checkoutRequestItem: CheckoutRequestItem):CheckoutResponseModel{
        return bookStoreAPI.checkout(checkoutRequestItem)
    }
    suspend fun buyNow(buyNowRequestItem: BuyNowRequestItem):BuyNowResponseModel{
        return bookStoreAPI.buyNow(buyNowRequestItem)
    }
    suspend fun deleteCart(cartId:Int):deleteItemCardModel{
        return bookStoreAPI.deleteCart(cartId)
    }
}
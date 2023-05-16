package com.eritlab.jexmon.presentation.screens.checkout_screen.component

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eritlab.jexmon.domain.item.BuyNowRequestItem
import com.eritlab.jexmon.domain.item.BuyNowResponseItem
import com.eritlab.jexmon.domain.model.buynow.ItemDetail
import com.eritlab.jexmon.domain.use_case.buynow.BuyNowUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheckoutBuyNowViewModel @Inject constructor(private val buyNowUseCase: BuyNowUseCase):ViewModel(){
    private val _checkoutBuyNow = MutableStateFlow<BuyNowResponseItem?>(null)
    val checkoutBuyNowResponse:StateFlow<BuyNowResponseItem?> get() = _checkoutBuyNow
    init {

    }
    fun checkoutBuyNow(userId:Int,userName:String,address:String,phone:String,itemDetail:ItemDetail,paymentMethod:String,totalPrice:Int){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val checkoutBuyNowToPage = BuyNowRequestItem(address,itemDetail,paymentMethod,phone,totalPrice,userId,userName)
                _checkoutBuyNow.value = buyNowUseCase(checkoutBuyNowToPage)
                
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}
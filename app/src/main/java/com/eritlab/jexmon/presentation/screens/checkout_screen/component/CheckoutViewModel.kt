package com.eritlab.jexmon.presentation.screens.checkout_screen.component

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eritlab.jexmon.domain.item.CheckoutRequestItem
import com.eritlab.jexmon.domain.item.CheckoutResponseItem
import com.eritlab.jexmon.domain.model.checkout.ItemDTOS
import com.eritlab.jexmon.domain.model.getCart.Item
import com.eritlab.jexmon.domain.use_case.checkout.CheckoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class CheckoutViewModel @Inject constructor(private val checkoutUseCase: CheckoutUseCase):ViewModel() {
    private val _checkout = MutableStateFlow<CheckoutResponseItem?>(null)
    val checkoutResponse: StateFlow<CheckoutResponseItem?> get() = _checkout
    init {

    }
    fun checkout(userId:Int, username:String, address:String,phone:String,itemDTOS:ArrayList<Item>,paymentMethod:String,totalPrice:Int){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val checkoutToPage = CheckoutRequestItem(address, itemDTOS, paymentMethod, phone, totalPrice, userId, username)
                _checkout.value = checkoutUseCase(checkoutToPage)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}
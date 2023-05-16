package com.eritlab.jexmon.presentation.screens.cart_screen.component

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eritlab.jexmon.domain.item.deleteItemCartItem
import com.eritlab.jexmon.domain.use_case.addCart.DeleteCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeleteCartViewModel @Inject constructor(private val deleteCartUseCase: DeleteCartUseCase):ViewModel(){
    private val _deleteCart = MutableStateFlow<deleteItemCartItem?>(null)
    val deleteCartResponse:StateFlow<deleteItemCartItem?> get() = _deleteCart
    init {

    }
    fun deleteCart(cartId:Int){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val deleteCart = deleteCartUseCase(cartId)
                _deleteCart.value = deleteCart
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}
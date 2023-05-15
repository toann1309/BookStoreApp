package com.eritlab.jexmon.presentation.screens.product_detail_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eritlab.jexmon.domain.item.AddCartRequestItem
import com.eritlab.jexmon.domain.item.AddCartResponseItem
import com.eritlab.jexmon.domain.use_case.addCart.AddCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCartViewModel @Inject constructor(private val addCartUseCase: AddCartUseCase):ViewModel(){
    private val _addCart = MutableStateFlow<AddCartResponseItem?>(null)
    val addCartResponse:StateFlow<AddCartResponseItem?> get() = _addCart
    init {

    }
    fun addCarts(product_id:Int, user_id:Int, quantity:Int){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val addCartToPage = AddCartRequestItem(product_id,user_id,quantity);
                _addCart.value = addCartUseCase(addCartToPage)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}
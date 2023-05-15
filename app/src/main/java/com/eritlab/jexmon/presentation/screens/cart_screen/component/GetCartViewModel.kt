package com.eritlab.jexmon.presentation.screens.cart_screen.component

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eritlab.jexmon.common.Constrains
import com.eritlab.jexmon.domain.item.GetCartItem
import com.eritlab.jexmon.domain.use_case.addCart.GetCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetCartViewModel @Inject constructor(private val getCartUseCase: GetCartUseCase, stateHandle: SavedStateHandle):ViewModel(){
    private val _getCart = MutableStateFlow<GetCartItem?>(null)
    val getCartResponse:StateFlow<GetCartItem?> get() = _getCart
    init {
//        val userId = stateHandle.get<String>(Constrains.USER_ID)
//        Log.e("userId",userId.toString())
//        getCart(userId!!.toInt())
    }
    fun getCart(userId:Int){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val getCarts = getCartUseCase(userId)
                _getCart.value = getCarts
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}
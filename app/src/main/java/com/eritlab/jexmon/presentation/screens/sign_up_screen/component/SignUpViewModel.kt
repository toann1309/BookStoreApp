package com.eritlab.jexmon.presentation.screens.sign_up_screen.component

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eritlab.jexmon.domain.item.UserRegisterRequestItem
import com.eritlab.jexmon.domain.item.UserRegisterResponseItem
import com.eritlab.jexmon.domain.use_case.user_login.UserRegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class SignUpViewModel @Inject constructor(private val userRegisterUseCase: UserRegisterUseCase): ViewModel(){
    private  val _register = MutableStateFlow<UserRegisterResponseItem?>(null)
    val registerResponse:StateFlow<UserRegisterResponseItem?> get() = _register

    fun register(firstName:String, lastName:String, email:String,password:String,address:String,phoneNumber:String){
        viewModelScope.launch {
            val registerToPage = UserRegisterRequestItem(address, email, firstName, lastName, password, phoneNumber)
            _register.value = userRegisterUseCase(registerToPage)
        }
    }
}
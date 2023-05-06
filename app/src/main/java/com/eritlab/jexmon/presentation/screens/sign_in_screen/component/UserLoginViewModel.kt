package com.eritlab.jexmon.presentation.screens.sign_in_screen.component

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eritlab.jexmon.domain.item.UserLoginItem
import com.eritlab.jexmon.domain.item.UserLoginResponseItem
import com.eritlab.jexmon.domain.use_case.user_login.UserLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserLoginViewModel @Inject constructor(private val userLoginUseCase: UserLoginUseCase):ViewModel(){
    private val _login = MutableStateFlow<UserLoginItem?>(null)
    val loginResponse:StateFlow<UserLoginItem?> get() = _login
    init {

    }
    fun login(email:String, password:String){
        viewModelScope.launch {
            val loginToPage = UserLoginResponseItem(email, password)
            _login.value = userLoginUseCase(loginToPage)
        }
    }
}
package com.eritlab.jexmon.presentation.screens.forget_password_screen.component

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eritlab.jexmon.domain.item.UserForgetPasswordRequestItem
import com.eritlab.jexmon.domain.item.UserForgetPasswordResponseItem
import com.eritlab.jexmon.domain.use_case.user_login.UserForgetPasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(private val userForgetPasswordUseCase: UserForgetPasswordUseCase):ViewModel() {
    private val _forgotPassword = MutableStateFlow<UserForgetPasswordResponseItem?>(null)
    val forgetPasswordResponse:StateFlow<UserForgetPasswordResponseItem?> get() = _forgotPassword
    init {

    }
    fun forgetPassword(email:String){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                val forgetPasswordToPage = UserForgetPasswordRequestItem(email)
                _forgotPassword.value = userForgetPasswordUseCase(forgetPasswordToPage)
            }
            catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}
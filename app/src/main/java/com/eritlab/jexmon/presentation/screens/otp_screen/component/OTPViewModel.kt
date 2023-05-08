package com.eritlab.jexmon.presentation.screens.otp_screen.component

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eritlab.jexmon.domain.item.UserOTPRequestItem
import com.eritlab.jexmon.domain.item.UserOTPResponseItem
import com.eritlab.jexmon.domain.use_case.user_login.UserOTPUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OTPViewModel @Inject constructor(private val userOTPUseCase: UserOTPUseCase):ViewModel(){
    private val _otp = MutableStateFlow<UserOTPResponseItem?>(null)
    val otpResponse:StateFlow<UserOTPResponseItem?> get() = _otp
    init {

    }
    fun otp(email:String, otpCode:String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val otpToPage = UserOTPRequestItem(email, otpCode)
                _otp.value = userOTPUseCase(otpToPage)
            }
            catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
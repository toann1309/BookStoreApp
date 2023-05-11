package com.eritlab.jexmon.presentation.screens.resetpassword.component

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eritlab.jexmon.domain.item.UserRegisterResponseItem
import com.eritlab.jexmon.domain.item.UserResetPasswordRequestItem
import com.eritlab.jexmon.domain.item.UserResetPasswordResponseItem
import com.eritlab.jexmon.domain.use_case.user_login.UserResetPasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ResetPasswordViewModel @Inject constructor(private val userResetPasswordUseCase: UserResetPasswordUseCase):ViewModel() {
    private val _resetPassword = MutableStateFlow<UserResetPasswordResponseItem?>(null)
    val resetPasswordResponse:StateFlow<UserResetPasswordResponseItem?> get() = _resetPassword
    init {

    }
    fun resetPassword(email:String, newPassword:String, otpCode:String){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                val resetPasswordToPage = UserResetPasswordRequestItem(email,newPassword,otpCode)
                _resetPassword.value = userResetPasswordUseCase(resetPasswordToPage)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}
package com.eritlab.jexmon.domain.service

import com.eritlab.jexmon.domain.item.UserLoginResponseItem
import com.eritlab.jexmon.domain.item.UserOTPRequestItem
import com.eritlab.jexmon.domain.item.UserRegisterRequestItem
import com.eritlab.jexmon.domain.model.userLogin.UserLoginModel
import com.eritlab.jexmon.domain.model.userOTP.UserOTPResponseModel
import com.eritlab.jexmon.domain.model.userRegister.UserRegisterResponseModel
import com.eritlab.jexmon.domain.repository.UserAPI
import javax.inject.Inject

class UserService @Inject constructor(private val userAPI: UserAPI) {
    suspend fun login(userLoginResponseModel: UserLoginResponseItem):UserLoginModel{
        return userAPI.login(userLoginResponseModel);
    }
    suspend fun register(userRegisterRequestItem: UserRegisterRequestItem):UserRegisterResponseModel{
        return userAPI.register(userRegisterRequestItem)
    }
    suspend fun otp(userOTPRequestItem: UserOTPRequestItem):UserOTPResponseModel{
        return userAPI.otp(userOTPRequestItem)
    }
}
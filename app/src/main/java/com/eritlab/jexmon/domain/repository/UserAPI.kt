package com.eritlab.jexmon.domain.repository
import com.eritlab.jexmon.domain.item.UserLoginResponseItem
import com.eritlab.jexmon.domain.item.UserOTPRequestItem
import com.eritlab.jexmon.domain.item.UserRegisterRequestItem
import com.eritlab.jexmon.domain.model.userLogin.UserLoginModel
import com.eritlab.jexmon.domain.model.userOTP.UserOTPResponseModel
import com.eritlab.jexmon.domain.model.userRegister.UserRegisterResponseModel
import retrofit2.http.Body
import retrofit2.http.POST

interface UserAPI {
    @POST("user/login")
    suspend fun login(@Body login: UserLoginResponseItem):UserLoginModel
    @POST("user/register")
    suspend fun register(@Body register: UserRegisterRequestItem):UserRegisterResponseModel
    @POST("user/otp")
    suspend fun otp(@Body otp:UserOTPRequestItem):UserOTPResponseModel
}
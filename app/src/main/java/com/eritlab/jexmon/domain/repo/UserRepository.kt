package com.eritlab.jexmon.domain.repo

import com.eritlab.jexmon.domain.item.*
import com.eritlab.jexmon.domain.service.UserService
import javax.inject.Inject

class UserRepository @Inject constructor(private val userService: UserService){
    suspend fun login(userLoginResponseItem: UserLoginResponseItem): UserLoginItem {
        val responese = userService.login(userLoginResponseItem);
        return responese.toGetUserLogin()
    }
    suspend fun register(userRegisterRequestItem: UserRegisterRequestItem):UserRegisterResponseItem{
        val response = userService.register(userRegisterRequestItem)
        return response.toGetUserRegisterResponse()
    }
    suspend fun otp(userOTPRequestItem: UserOTPRequestItem):UserOTPResponseItem{
        val response = userService.otp(userOTPRequestItem)
        return response.toGetUserOTPResponse()
    }
    suspend fun forgetPassword(userForgetPasswordRequestItem: UserForgetPasswordRequestItem):UserForgetPasswordResponseItem{
        val response = userService.forgetPassword(userForgetPasswordRequestItem)
        return response.toGetUserForgetPasswordResponse()
    }
    suspend fun resetPassword(userResetPasswordRequestItem: UserResetPasswordRequestItem):UserResetPasswordResponseItem{
        val response = userService.resetPassword(userResetPasswordRequestItem)
        return response.toGetUserResetPasswordResponse()
    }
    suspend fun getUser(userId:Int):UserDetailItem{
        val response = userService.getUser(userId)
        return response.toGetUserDetail()
    }
}
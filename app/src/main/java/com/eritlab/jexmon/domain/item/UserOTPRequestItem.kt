package com.eritlab.jexmon.domain.item

import com.eritlab.jexmon.domain.model.userOTP.UserOTPRequestModel

data class UserOTPRequestItem(
    val email:String,
    val otpCode:String
)
fun UserOTPRequestModel.toGetUserOTPRequest() = UserOTPRequestItem(email, otpCode)

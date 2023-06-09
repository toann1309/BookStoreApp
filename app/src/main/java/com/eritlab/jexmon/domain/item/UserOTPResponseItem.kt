package com.eritlab.jexmon.domain.item

import com.eritlab.jexmon.domain.model.userOTP.UserOTPResponseModel

data class UserOTPResponseItem(
    val email: String,
    val otp: Boolean,
    val status: String
)
fun UserOTPResponseModel.toGetUserOTPResponse() = UserOTPResponseItem(email, otp, status)

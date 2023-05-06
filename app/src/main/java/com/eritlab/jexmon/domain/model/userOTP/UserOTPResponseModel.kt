package com.eritlab.jexmon.domain.model.userOTP

data class UserOTPResponseModel(
    val email: String,
    val otp: Boolean,
    val otpCode: String,
    val status: String
)
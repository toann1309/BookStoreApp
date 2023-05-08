package com.eritlab.jexmon.domain.model.userOTP

data class UserOTPRequestModel(
    val email: String,
    val otpCode: String
)
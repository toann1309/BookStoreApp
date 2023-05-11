package com.eritlab.jexmon.domain.model.userResetPassword

data class UserResetPasswordResponseModel(
    val email: String,
    val newPassword: String,
    val otpCode: String,
    val status: String
)
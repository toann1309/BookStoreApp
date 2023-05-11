package com.eritlab.jexmon.domain.model.userResetPassword

data class UserResetPasswordRequestModel(
    val email: String,
    val newPassword: String,
    val otpCode: String
)
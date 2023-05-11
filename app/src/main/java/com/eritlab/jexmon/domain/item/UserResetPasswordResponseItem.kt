package com.eritlab.jexmon.domain.item

import com.eritlab.jexmon.domain.model.userResetPassword.UserResetPasswordResponseModel

data class UserResetPasswordResponseItem(
    val email: String,
    val newPassword: String,
    val otpCode: String,
    val status: String
)
fun UserResetPasswordResponseModel.toGetUserResetPasswordResponse() = UserResetPasswordResponseItem(email, newPassword, otpCode, status)

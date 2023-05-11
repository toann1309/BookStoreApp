package com.eritlab.jexmon.domain.item

import com.eritlab.jexmon.domain.model.userResetPassword.UserResetPasswordRequestModel

data class UserResetPasswordRequestItem(
    val email: String,
    val newPassword: String,
    val otpCode: String
)
fun UserResetPasswordRequestModel.toGetUserResetPasswordRequest() = UserResetPasswordRequestItem(email, newPassword, otpCode)
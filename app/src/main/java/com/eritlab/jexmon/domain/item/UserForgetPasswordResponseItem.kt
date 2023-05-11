package com.eritlab.jexmon.domain.item

import com.eritlab.jexmon.domain.model.userForgetPassword.UserForgetPasswordResponseModel

data class UserForgetPasswordResponseItem(
    val email:String,
    val status:String
)
fun UserForgetPasswordResponseModel.toGetUserForgetPasswordResponse() = UserForgetPasswordResponseItem(email, status)

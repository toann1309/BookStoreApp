package com.eritlab.jexmon.domain.item

import com.eritlab.jexmon.domain.model.userForgetPassword.UserForgetPasswordRequestModel

data class UserForgetPasswordRequestItem(
    val email:String
)
fun UserForgetPasswordRequestModel.toGetUserForgetPasswordRequest() = UserForgetPasswordRequestItem(email)
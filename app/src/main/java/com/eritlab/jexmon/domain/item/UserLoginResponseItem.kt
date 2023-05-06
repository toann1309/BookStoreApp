package com.eritlab.jexmon.domain.item

import com.eritlab.jexmon.domain.model.userLogin.UserLoginResponseModel

data class UserLoginResponseItem(
    val email:String,
    val password:String
)
fun UserLoginResponseModel.toGetUserLoginResponse() = UserLoginResponseItem(email, password)

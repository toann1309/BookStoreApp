package com.eritlab.jexmon.domain.item

import com.eritlab.jexmon.domain.model.userLogin.UserLoginModel

data class UserLoginItem(
    val email: String,
    val password: String,
    val status: String,
    val id:Int
)
fun UserLoginModel.toGetUserLogin() = UserLoginItem(email, password, status,id)

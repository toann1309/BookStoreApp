package com.eritlab.jexmon.domain.item

import com.eritlab.jexmon.domain.model.userRegister.UserRegisterRequestModel

data class UserRegisterRequestItem(
    val address: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val password: String,
    val phoneNumber: String
)
fun UserRegisterRequestModel.toGetUserRegister() = UserRegisterRequestItem(address, email, firstName, lastName, password, phoneNumber)
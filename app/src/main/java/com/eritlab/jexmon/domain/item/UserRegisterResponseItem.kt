package com.eritlab.jexmon.domain.item

import com.eritlab.jexmon.domain.model.userRegister.UserRegisterResponseModel

data class UserRegisterResponseItem(
    val defaultAddress: String,
    val email: String,
    val firstName: String,
    val id: Int,
    val isActive: Boolean,
    val lastName: String,
    val password: String,
    val phoneNumber: String,
    val roles: String,
    val verificationCode: String
)
fun UserRegisterResponseModel.toGetUserRegisterResponse() = UserRegisterResponseItem(defaultAddress, email, firstName, id, isActive, lastName, password, phoneNumber, roles, verificationCode)

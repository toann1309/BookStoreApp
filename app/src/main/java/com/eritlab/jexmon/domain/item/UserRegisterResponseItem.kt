package com.eritlab.jexmon.domain.item

import com.eritlab.jexmon.domain.model.userRegister.UserRegisterResponseModel

data class UserRegisterResponseItem(
    val addresses: Any,
    val defaultAddress: String,
    val email: String,
    val firstName: String,
    val id: Int,
    val isActive: Boolean,
    val lastName: String,
    val orders: Any,
    val password: String,
    val payments: Any,
    val phoneNumber: String,
    val roles: String,
    val verificationCode: String
)
fun UserRegisterResponseModel.toGetUserRegisterResponse() = UserRegisterResponseItem(addresses, defaultAddress, email, firstName, id, isActive, lastName, orders, password, payments, phoneNumber, roles, verificationCode)

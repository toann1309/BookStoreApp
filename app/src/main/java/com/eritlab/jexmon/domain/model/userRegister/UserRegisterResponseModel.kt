package com.eritlab.jexmon.domain.model.userRegister

data class UserRegisterResponseModel(
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
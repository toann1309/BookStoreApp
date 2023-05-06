package com.eritlab.jexmon.domain.model.userRegister

data class UserRegisterRequestModel(
    val address: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val password: String,
    val phoneNumber: String
)
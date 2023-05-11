package com.eritlab.jexmon.domain.model.userDetail

data class UserDetailModel(
    val address: String,
    val email: String,
    val firstName: String,
    val id: Int,
    val isActive: Boolean,
    val lastName: String,
    val password: String,
    val phoneNumber: String,
    val role: String
)
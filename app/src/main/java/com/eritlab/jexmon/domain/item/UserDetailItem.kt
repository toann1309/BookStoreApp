package com.eritlab.jexmon.domain.item

import com.eritlab.jexmon.domain.model.userDetail.UserDetailModel

data class UserDetailItem(
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
fun UserDetailModel.toGetUserDetail() = UserDetailItem(address, email, firstName, id, isActive, lastName, password, phoneNumber, role)

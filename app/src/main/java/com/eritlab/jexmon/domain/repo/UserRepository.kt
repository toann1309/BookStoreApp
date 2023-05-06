package com.eritlab.jexmon.domain.repo

import com.eritlab.jexmon.domain.item.*
import com.eritlab.jexmon.domain.service.UserService
import javax.inject.Inject

class UserRepository @Inject constructor(private val userService: UserService){
    suspend fun login(userLoginResponseItem: UserLoginResponseItem): UserLoginItem {
        val responese = userService.login(userLoginResponseItem);
        return responese.toGetUserLogin()
    }
    suspend fun register(userRegisterRequestItem: UserRegisterRequestItem):UserRegisterResponseItem{
        val response = userService.register(userRegisterRequestItem)
        return response.toGetUserRegisterResponse()
    }
}
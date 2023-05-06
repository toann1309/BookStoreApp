package com.eritlab.jexmon.domain.use_case.user_login

import com.eritlab.jexmon.domain.item.UserRegisterRequestItem
import com.eritlab.jexmon.domain.item.UserRegisterResponseItem
import com.eritlab.jexmon.domain.repo.UserRepository
import javax.inject.Inject

class UserRegisterUseCase @Inject constructor(private val userRepository: UserRepository){
    suspend operator fun invoke(userRegisterRequestItem: UserRegisterRequestItem):UserRegisterResponseItem{
        return userRepository.register(userRegisterRequestItem)
    }
}
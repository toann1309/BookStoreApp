package com.eritlab.jexmon.domain.use_case.user_login

import com.eritlab.jexmon.domain.item.UserLoginItem
import com.eritlab.jexmon.domain.item.UserLoginResponseItem
import com.eritlab.jexmon.domain.repo.UserRepository
import javax.inject.Inject

class UserLoginUseCase @Inject constructor(private val userRepository: UserRepository){
    suspend operator fun invoke(userLoginResponseItem: UserLoginResponseItem):UserLoginItem{
        return userRepository.login(userLoginResponseItem);
    }
}
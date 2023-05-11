package com.eritlab.jexmon.domain.use_case.user_login

import com.eritlab.jexmon.domain.item.UserForgetPasswordRequestItem
import com.eritlab.jexmon.domain.item.UserForgetPasswordResponseItem
import com.eritlab.jexmon.domain.repo.UserRepository
import javax.inject.Inject

class UserForgetPasswordUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(userForgetPasswordRequestItem: UserForgetPasswordRequestItem):UserForgetPasswordResponseItem{
        return userRepository.forgetPassword(userForgetPasswordRequestItem)
    }
}
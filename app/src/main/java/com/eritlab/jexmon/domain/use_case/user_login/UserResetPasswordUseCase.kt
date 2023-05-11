package com.eritlab.jexmon.domain.use_case.user_login

import com.eritlab.jexmon.domain.item.UserResetPasswordRequestItem
import com.eritlab.jexmon.domain.item.UserResetPasswordResponseItem
import com.eritlab.jexmon.domain.repo.UserRepository
import javax.inject.Inject

class UserResetPasswordUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(userResetPasswordRequestItem: UserResetPasswordRequestItem):UserResetPasswordResponseItem{
        return userRepository.resetPassword(userResetPasswordRequestItem)
    }
}
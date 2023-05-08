package com.eritlab.jexmon.domain.use_case.user_login

import com.eritlab.jexmon.domain.item.UserOTPRequestItem
import com.eritlab.jexmon.domain.item.UserOTPResponseItem
import com.eritlab.jexmon.domain.repo.UserRepository
import javax.inject.Inject

class UserOTPUseCase @Inject constructor(private val userRepository: UserRepository){
    suspend operator fun invoke(userOTPRequestItem: UserOTPRequestItem):UserOTPResponseItem{
        return userRepository.otp(userOTPRequestItem)
    }
}
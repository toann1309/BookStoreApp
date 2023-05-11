package com.eritlab.jexmon.domain.use_case.user_login

import com.eritlab.jexmon.domain.item.UserDetailItem
import com.eritlab.jexmon.domain.repo.UserRepository
import javax.inject.Inject

class UserDetailUseCase @Inject constructor(private val userRepository: UserRepository){
    suspend operator fun invoke(userId:Int):UserDetailItem{
        return userRepository.getUser(userId)
    }
}
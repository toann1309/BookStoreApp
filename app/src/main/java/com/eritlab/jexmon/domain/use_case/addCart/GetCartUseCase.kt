package com.eritlab.jexmon.domain.use_case.addCart

import com.eritlab.jexmon.domain.item.GetCartItem
import com.eritlab.jexmon.domain.repo.CartRepository
import javax.inject.Inject

class GetCartUseCase @Inject constructor(private val cartRepository: CartRepository){
    suspend operator fun invoke(userId:Int):GetCartItem{
        return cartRepository.getCart(userId)
    }
}
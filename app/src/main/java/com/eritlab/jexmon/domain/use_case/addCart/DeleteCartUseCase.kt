package com.eritlab.jexmon.domain.use_case.addCart

import com.eritlab.jexmon.domain.item.deleteItemCartItem
import com.eritlab.jexmon.domain.repo.CartRepository
import javax.inject.Inject

class DeleteCartUseCase @Inject constructor(private val cartRepository: CartRepository){
    suspend operator fun invoke(cartId:Int):deleteItemCartItem{
        return cartRepository.deleteCart(cartId)
    }
}
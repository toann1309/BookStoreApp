package com.eritlab.jexmon.domain.use_case.addCart

import com.eritlab.jexmon.domain.item.AddCartRequestItem
import com.eritlab.jexmon.domain.item.AddCartResponseItem
import com.eritlab.jexmon.domain.repo.CartRepository
import javax.inject.Inject

class AddCartUseCase @Inject constructor(private val cartRepository: CartRepository){
    suspend operator fun invoke(addCartRequestItem: AddCartRequestItem):AddCartResponseItem{
        return cartRepository.addCart(addCartRequestItem)
    }
}
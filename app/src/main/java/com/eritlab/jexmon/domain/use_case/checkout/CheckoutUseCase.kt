package com.eritlab.jexmon.domain.use_case.checkout

import com.eritlab.jexmon.domain.item.CheckoutRequestItem
import com.eritlab.jexmon.domain.item.CheckoutResponseItem
import com.eritlab.jexmon.domain.repo.CartRepository
import javax.inject.Inject

class CheckoutUseCase @Inject constructor(private val cartRepository: CartRepository){
    suspend operator fun invoke(checkoutRequestItem: CheckoutRequestItem):CheckoutResponseItem{
        return cartRepository.chẹckout(checkoutRequestItem)
    }
}
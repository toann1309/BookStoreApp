package com.eritlab.jexmon.domain.use_case.buynow

import com.eritlab.jexmon.domain.item.BuyNowRequestItem
import com.eritlab.jexmon.domain.item.BuyNowResponseItem
import com.eritlab.jexmon.domain.repo.CartRepository
import javax.inject.Inject

class BuyNowUseCase @Inject constructor(private val cartRepository: CartRepository){
    suspend operator fun invoke(buyNowRequestItem: BuyNowRequestItem):BuyNowResponseItem{
        return cartRepository.buyNow(buyNowRequestItem)
    }
}
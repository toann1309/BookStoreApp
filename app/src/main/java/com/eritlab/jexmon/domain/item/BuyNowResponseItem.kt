package com.eritlab.jexmon.domain.item

import com.eritlab.jexmon.domain.model.buynow.BuyNowResponseModel

data class BuyNowResponseItem(
    val success:String,
    val message:String,
)
fun BuyNowResponseModel.toGetBuyNowResponse() = BuyNowResponseItem(success, message)
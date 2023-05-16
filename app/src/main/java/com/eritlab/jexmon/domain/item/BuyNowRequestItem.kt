package com.eritlab.jexmon.domain.item

import com.eritlab.jexmon.domain.model.buynow.BuyNowRequestModel
import com.eritlab.jexmon.domain.model.buynow.ItemDetail

data class BuyNowRequestItem(
    val address: String,
    val itemDetail: ItemDetail,
    val paymentMethod: String,
    val phone: String,
    val totalPrice: Int,
    val userId: Int,
    val userName: String
)
fun BuyNowRequestModel.toBuyNowRequest() = BuyNowRequestItem(address, itemDetail, paymentMethod, phone, totalPrice, userId, userName)

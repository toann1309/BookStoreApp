package com.eritlab.jexmon.domain.item

import com.eritlab.jexmon.domain.model.addcart.AddCartRequestModel

data class AddCartRequestItem(
    val product_id:Int,
    val user_id:Int,
    val quantity:Int
)
fun AddCartRequestModel.toGetAddCartRequest() = AddCartRequestItem(product_id, user_id, quantity)
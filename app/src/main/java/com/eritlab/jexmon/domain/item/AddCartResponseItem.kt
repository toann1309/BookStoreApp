package com.eritlab.jexmon.domain.item

import com.eritlab.jexmon.domain.model.addcart.AddCartResponseModel

data class AddCartResponseItem(
    val success:String,
    val message:String,
)
fun AddCartResponseModel.toGetAddCartResponse() = AddCartResponseItem(success, message)

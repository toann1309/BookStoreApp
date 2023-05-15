package com.eritlab.jexmon.domain.model.addcart

data class AddCartRequestModel(
    val product_id:Int,
    val user_id:Int,
    val quantity:Int
)

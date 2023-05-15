package com.eritlab.jexmon.domain.item

import com.eritlab.jexmon.domain.model.getCart.GetCartModel
import com.eritlab.jexmon.domain.model.getCart.Item

data class GetCartItem(
    val id: Int,
    val itemList: List<Item>,
    val userId: Int
)
fun GetCartModel.toGetCart() = GetCartItem(id, itemList, userId)

package com.eritlab.jexmon.domain.model.getCart

data class GetCartModel(
    val id: Int,
    val itemList: List<Item>,
    val userId: Int
)
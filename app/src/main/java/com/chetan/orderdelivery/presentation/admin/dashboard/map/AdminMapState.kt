package com.chetan.orderdelivery.presentation.admin.dashboard.map

import com.chetan.orderdelivery.data.model.order.GetFoodOrder

data class AdminMapState(
    val orderedUserList: List<GetFoodOrder> = emptyList()
)

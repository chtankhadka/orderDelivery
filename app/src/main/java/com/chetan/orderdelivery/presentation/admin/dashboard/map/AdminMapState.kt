package com.chetan.orderdelivery.presentation.admin.dashboard.map

import com.chetan.orderdelivery.data.model.order.RequestFoodOrder

data class AdminMapState(
    val orderedUserList: List<RequestFoodOrder> = emptyList(),
    val userDetails: RequestFoodOrder = RequestFoodOrder()
)

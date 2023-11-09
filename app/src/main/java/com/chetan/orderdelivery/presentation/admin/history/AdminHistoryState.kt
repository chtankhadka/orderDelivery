package com.chetan.orderdelivery.presentation.admin.history

import com.chetan.orderdelivery.data.model.order.RequestFoodOrder

data class AdminHistoryState(
    val historyList : List<RequestFoodOrder> = emptyList()
)

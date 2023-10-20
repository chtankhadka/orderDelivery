package com.chetan.orderdelivery.presentation.user.notification

import com.chetan.orderdelivery.data.model.GetFoodResponse

data class NotificationState(
    val allFoodList: List<GetFoodResponse> = emptyList()
)

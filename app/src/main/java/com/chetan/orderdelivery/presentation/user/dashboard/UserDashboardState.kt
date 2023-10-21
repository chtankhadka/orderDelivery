package com.chetan.orderdelivery.presentation.user.dashboard

import com.chetan.orderdelivery.data.model.GetFoodResponse
import com.chetan.orderdelivery.presentation.common.components.OrderDeliveryScreenState
import com.chetan.orderdelivery.presentation.common.components.dialogs.Message

data class UserDashboardState(
    val darkMode: Boolean = false,
    val profileUrl: String = "",
    val allFoods : List<GetFoodResponse> = emptyList(),
    override val infoMsg: Message? = null
) : OrderDeliveryScreenState(infoMsg)
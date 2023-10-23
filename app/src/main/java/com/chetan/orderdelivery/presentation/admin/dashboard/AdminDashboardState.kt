package com.chetan.orderdelivery.presentation.admin.dashboard

import com.chetan.orderdelivery.data.model.RealtimeModelResponse
import com.chetan.orderdelivery.presentation.common.components.OrderDeliveryScreenState
import com.chetan.orderdelivery.presentation.common.components.dialogs.Message

data class AdminDashboardState(
    val darkMode: Boolean = false,
    val isNewOrder : Boolean = false,
    val newRequestList:  List<RealtimeModelResponse> = emptyList(),
    override val infoMsg: Message? = null
) : OrderDeliveryScreenState(infoMsg)
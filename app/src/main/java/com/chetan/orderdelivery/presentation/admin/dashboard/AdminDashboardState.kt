package com.chetan.orderdelivery.presentation.admin.dashboard

import com.chetan.orderdelivery.data.model.RealtimeModelResponse

data class AdminDashboardState(
    val darkMode: Boolean = false,
    val isNewOrder : Boolean = false,
    val newRequestList:  List<RealtimeModelResponse> = emptyList(),
)

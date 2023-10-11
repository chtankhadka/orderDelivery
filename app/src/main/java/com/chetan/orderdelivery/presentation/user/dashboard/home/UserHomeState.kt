package com.chetan.orderdelivery.presentation.user.dashboard.home

import com.chetan.orderdelivery.data.model.RealtimeModelResponse

data class UserHomeState(
    val name: List<RealtimeModelResponse> = emptyList()
)
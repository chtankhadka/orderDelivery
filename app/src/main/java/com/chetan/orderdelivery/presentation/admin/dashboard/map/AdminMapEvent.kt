package com.chetan.orderdelivery.presentation.admin.dashboard.map

sealed interface AdminMapEvent{
    data class OnClickWindoInfo(val orderId: String): AdminMapEvent
}
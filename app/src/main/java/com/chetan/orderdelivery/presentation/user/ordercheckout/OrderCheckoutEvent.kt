package com.chetan.orderdelivery.presentation.user.ordercheckout

sealed interface OrderCheckoutEvent{
    data class LocationAddress(val value: String): OrderCheckoutEvent
    data class Location(val value: String) : OrderCheckoutEvent
    data object OrderNow: OrderCheckoutEvent
}
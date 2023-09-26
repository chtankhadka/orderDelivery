package com.chetan.orderdelivery.presentation.user.ordercheckout

sealed interface OrderCheckoutEvent{
    data object test: OrderCheckoutEvent
}
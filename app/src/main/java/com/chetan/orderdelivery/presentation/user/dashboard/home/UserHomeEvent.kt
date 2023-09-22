package com.chetan.orderdelivery.presentation.user.dashboard.home

sealed interface UserHomeEvent{
    data object AddToCart: UserHomeEvent
}
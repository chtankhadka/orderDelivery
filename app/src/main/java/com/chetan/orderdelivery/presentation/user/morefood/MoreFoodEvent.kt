package com.chetan.orderdelivery.presentation.user.morefood

sealed interface MoreFoodEvent{
    data object Test: MoreFoodEvent
}
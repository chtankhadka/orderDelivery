package com.chetan.orderdelivery.presentation.user.foodorder


sealed interface FoodOrderEvent{
    class OrderFood(val value: String): FoodOrderEvent
}
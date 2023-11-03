package com.chetan.orderdelivery.presentation.user.myorder

sealed interface MyOrderEvent{
    data object test: MyOrderEvent
}
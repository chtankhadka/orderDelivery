package com.chetan.orderdelivery.presentation.user.dashboard.history

sealed interface UserHistoryEvent{
    data object RateIt: UserHistoryEvent
}
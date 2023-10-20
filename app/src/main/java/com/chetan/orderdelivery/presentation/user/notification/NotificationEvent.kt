package com.chetan.orderdelivery.presentation.user.notification

sealed interface NotificationEvent{
    data object Test: NotificationEvent
}
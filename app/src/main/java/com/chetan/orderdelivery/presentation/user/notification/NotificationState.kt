package com.chetan.orderdelivery.presentation.user.notification

import com.chetan.orderdelivery.data.model.StoreNotificationRequestResponse

data class NotificationState(
    val notificationList: List<StoreNotificationRequestResponse> = emptyList()
)

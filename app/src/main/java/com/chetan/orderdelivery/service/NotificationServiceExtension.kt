package com.chetan.orderdelivery.service

import com.onesignal.OneSignal
import com.onesignal.common.OneSignalUtils
import com.onesignal.notifications.INotificationReceivedEvent
import com.onesignal.notifications.INotificationServiceExtension

class NotificationServiceExtension : INotificationServiceExtension {
    override fun onNotificationReceived(event: INotificationReceivedEvent) {
        println(" I am called")
    }
}
package com.chetan.orderdelivery.service

import com.chetan.orderdelivery.data.local.Preference
import com.chetan.orderdelivery.data.model.StoreNotificationRequestResponse
import com.chetan.orderdelivery.data.repositoryImpl.OneSignalRepositoryImpl
import com.chetan.orderdelivery.di.HiltEntryPoint
import com.chetan.orderdelivery.domain.repository.FirestoreRepository
import com.chetan.orderdelivery.domain.repository.OneSignalRepository
import com.chetan.orderdelivery.domain.use_cases.firestore.FirestoreUseCases
import com.chetan.orderdelivery.domain.use_cases.firestore.SetNotification
import com.onesignal.OneSignal
import com.onesignal.common.OneSignalUtils
import com.onesignal.notifications.INotificationReceivedEvent
import com.onesignal.notifications.INotificationServiceExtension
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class NotificationServiceExtension
  : INotificationServiceExtension {
    override fun onNotificationReceived(event: INotificationReceivedEvent) {
        try {
            val hiltEntryPoint =
                EntryPointAccessors.fromApplication(event.context, HiltEntryPoint::class.java)
            val firestoreUseCases = hiltEntryPoint.firestoreUseCases()
            println("----------------------------------------------------")
            println(event.notification)
            CoroutineScope(SupervisorJob()).launch {
                firestoreUseCases.setNotification(
                    StoreNotificationRequestResponse(
                        body = "",
                        title = "",
                        time = (11).toString(),
                        readNotice = false,
                    )
                )
            }
        } catch (e: Exception){

        }


    }
}
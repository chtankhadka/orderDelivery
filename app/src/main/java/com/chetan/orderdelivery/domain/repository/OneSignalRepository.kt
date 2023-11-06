package com.chetan.orderdelivery.domain.repository

import com.chetan.orderdelivery.data.Resource
import com.chetan.orderdelivery.data.model.PushNotificationRequest
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface OneSignalRepository {

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
        "Authorization: Basic MzY2NGEzYmUtY2E2OS00NDRmLWJhNDItYTlkZTJkM2ZhMTU2"
    )
    @POST("notifications")
    suspend fun pushNotification(
        @Body requestBody: PushNotificationRequest
    ): Resource<Boolean>
}
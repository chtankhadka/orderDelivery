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
        "Authorization: Basic NTU2OTYxMTctYzE5Zi00OTk2LWEyOTUtNTdiYjhmZGIxNzli"
    )
    @POST("notifications")
    suspend fun pushNotification(
        @Body requestBody: PushNotificationRequest
    ): Resource<Any>
}
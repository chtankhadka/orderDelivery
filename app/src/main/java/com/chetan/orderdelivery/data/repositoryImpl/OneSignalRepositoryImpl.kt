package com.chetan.orderdelivery.data.repositoryImpl

import com.chetan.orderdelivery.data.Resource
import com.chetan.orderdelivery.data.model.PushNotificationRequest
import com.chetan.orderdelivery.domain.repository.OneSignalRepository
import retrofit2.HttpException
import javax.inject.Inject

class OneSignalRepositoryImpl @Inject constructor(
    private val oneSignal : OneSignalRepository
) : OneSignalRepository{
    override suspend fun pushNotification(requestBody: PushNotificationRequest): Resource<Any> {
        return try {
            val response = oneSignal.pushNotification(requestBody)
            Resource.Success(response)
        }catch (e: HttpException){
            e.printStackTrace()
            Resource.Failure(e)
        }
        catch (e: Exception){
            e.printStackTrace()
            Resource.Failure(e)
        }
    }
}
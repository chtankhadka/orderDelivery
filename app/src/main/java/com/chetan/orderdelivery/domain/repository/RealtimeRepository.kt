package com.chetan.orderdelivery.domain.repository

import com.chetan.orderdelivery.data.Resource
import com.chetan.orderdelivery.data.model.RealtimeModelResponse
import kotlinx.coroutines.flow.Flow

interface RealtimeRepository {

    suspend fun insert(
        item: RealtimeModelResponse.RealTimeNewOrderRequest
    ) : Resource<String>

    fun getItems() : Flow<Resource<List<RealtimeModelResponse>>>

    suspend fun deleteOrders() : Resource<Boolean>
}
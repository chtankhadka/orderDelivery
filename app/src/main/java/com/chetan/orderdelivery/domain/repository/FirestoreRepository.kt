package com.chetan.orderdelivery.domain.repository

import com.chetan.orderdelivery.data.Resource
import com.chetan.orderdelivery.data.model.order.GetFoodOrder
import com.chetan.orderdelivery.data.model.order.RequestFoodOrder

interface FirestoreRepository {
    suspend fun orderFood(
        data : List<RequestFoodOrder>
    ) : Resource<Any>

    suspend fun getFoodOrders(): Resource<List<GetFoodOrder>>
}
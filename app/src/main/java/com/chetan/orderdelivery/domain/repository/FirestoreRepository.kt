package com.chetan.orderdelivery.domain.repository

import com.chetan.orderdelivery.data.Resource
import com.chetan.orderdelivery.data.model.AddFoodRequest
import com.chetan.orderdelivery.data.model.Rating
import com.chetan.orderdelivery.data.model.order.GetFoodOrder
import com.chetan.orderdelivery.data.model.order.GetFoodResponse
import com.chetan.orderdelivery.data.model.order.RequestFoodOrder

interface FirestoreRepository {
    suspend fun orderFood(
        data : List<RequestFoodOrder>
    ) : Resource<Any>

    suspend fun rating(
        data: Rating
    ) : Resource<Boolean>

    suspend fun getFoods() : Resource<List<GetFoodResponse>>



    //admin
    suspend fun getFoodOrders(): Resource<List<GetFoodOrder>>
    suspend fun addFood(
        data: AddFoodRequest
    ): Resource<Boolean>
}
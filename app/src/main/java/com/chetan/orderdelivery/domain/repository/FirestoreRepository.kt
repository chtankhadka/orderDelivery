package com.chetan.orderdelivery.domain.repository

import com.chetan.orderdelivery.data.Resource
import com.chetan.orderdelivery.data.model.AddFoodRequest
import com.chetan.orderdelivery.data.model.RatingRequestResponse
import com.chetan.orderdelivery.data.model.order.GetFoodOrder
import com.chetan.orderdelivery.data.model.GetFoodResponse
import com.chetan.orderdelivery.data.model.order.RequestFoodOrder

interface FirestoreRepository {
    suspend fun orderFood(
        data : List<RequestFoodOrder>
    ) : Resource<Any>

    suspend fun rating(
        data: RatingRequestResponse
    ) : Resource<Boolean>

    suspend fun getFoods() : Resource<List<GetFoodResponse>>
    suspend fun getFoodsForUpdate() : Resource<List<GetFoodResponse>>
    suspend fun getRating() : Resource<List<RatingRequestResponse>>



    //admin
    suspend fun getFoodOrders(): Resource<List<GetFoodOrder>>
    suspend fun addFood(
        data: AddFoodRequest
    ): Resource<Boolean>
}
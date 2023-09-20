package com.chetan.orderdelivery.domain.repository

import com.chetan.orderdelivery.data.Resource
import com.chetan.orderdelivery.data.model.order.GetFoodOrder
import com.google.android.gms.maps.model.LatLng

interface FirestoreRepository {
    suspend fun orderFood(
        location : String
    ) : Boolean

    suspend fun getFoodOrders(): Resource<List<GetFoodOrder>>
}
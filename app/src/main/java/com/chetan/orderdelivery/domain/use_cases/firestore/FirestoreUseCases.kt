package com.chetan.orderdelivery.domain.use_cases.firestore

data class FirestoreUseCases(
    val orderFood: OrderFood,
    val getFoodOrders : GetFoodOrders,
    val addFood : AddFood,
    val rateIt : RateIt
)

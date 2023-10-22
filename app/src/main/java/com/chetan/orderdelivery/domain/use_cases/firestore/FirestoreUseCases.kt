package com.chetan.orderdelivery.domain.use_cases.firestore

data class FirestoreUseCases(
    val orderFood: OrderFood,
    val getFoods: GetFoods,
    val getFoodItem: GetFoodItem,
    val getFoodOrders : GetFoodOrders,
    val addFood : AddFood,

    val getCartItems : GetCartItems,
    val deleteCartItem: DeleteCartItem,
    val addToCart : AddToCart,

    val rateIt : RateIt,
    val updateRating: UpdateRating,
    val getFoodsForUpdate : GetFoodsForUpdate
)

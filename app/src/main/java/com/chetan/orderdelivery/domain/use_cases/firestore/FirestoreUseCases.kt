package com.chetan.orderdelivery.domain.use_cases.firestore

data class FirestoreUseCases(
    val setAddress: SetAddress,

    val orderFood: OrderFood,
    val getFoods: GetFoods,
    val getFoodItem: GetFoodItem,
    val getFoodOrders : GetFoodOrders,
    val getFoodOrderDetails: GetFoodOrderDetails,
    val addFood : AddFood,

    val getCartItems : GetCartItems,
    val deleteCartItem: DeleteCartItem,
    val addToCart : AddToCart,

    val rateIt : RateIt,
    val updateRating: UpdateRating,
    val getFoodsForUpdate : GetFoodsForUpdate
)

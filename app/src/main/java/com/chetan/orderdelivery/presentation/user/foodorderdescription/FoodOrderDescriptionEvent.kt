package com.chetan.orderdelivery.presentation.user.foodorderdescription


sealed interface FoodOrderDescriptionEvent{
    class OrderFood(val value: String): FoodOrderDescriptionEvent
    data object DismissInfoMsg : FoodOrderDescriptionEvent
    data class AddToCart(val value: String): FoodOrderDescriptionEvent
    data class GetFoodItemDetails(val value: String) : FoodOrderDescriptionEvent
    data object IncreaseQuantity : FoodOrderDescriptionEvent
    data object DecreaseQuantity : FoodOrderDescriptionEvent
}
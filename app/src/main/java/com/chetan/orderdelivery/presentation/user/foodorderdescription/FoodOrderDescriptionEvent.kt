package com.chetan.orderdelivery.presentation.user.foodorderdescription


sealed interface FoodOrderDescriptionEvent{
    class OrderFood(val value: String): FoodOrderDescriptionEvent
    data class GetFoodItemDetails(val value: String) : FoodOrderDescriptionEvent
}
package com.chetan.orderdelivery.presentation.admin.food.addfood

sealed interface AddFoodEvent{
    data class OnFoodNameChange(val value: String) : AddFoodEvent
    data class OnFoodDetailsChange(val value: String) : AddFoodEvent

}
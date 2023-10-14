package com.chetan.orderdelivery.presentation.admin.food.ratingUpdate

sealed interface RatingUpdateEvent{
    data class UpdateThis(val value : String) : RatingUpdateEvent
}
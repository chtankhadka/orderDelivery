package com.chetan.orderdelivery.presentation.admin.food.ratingUpdate

import com.chetan.orderdelivery.data.model.GetFoodResponse

data class RatingUpdateState(
    val item: String = "",
    val foodList : List<GetFoodResponse> = emptyList()
)

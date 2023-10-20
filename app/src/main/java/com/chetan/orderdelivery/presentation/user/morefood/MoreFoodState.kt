package com.chetan.orderdelivery.presentation.user.morefood

import com.chetan.orderdelivery.data.model.GetFoodResponse

data class MoreFoodState(
    val allFoodList: List<GetFoodResponse> = emptyList()
)

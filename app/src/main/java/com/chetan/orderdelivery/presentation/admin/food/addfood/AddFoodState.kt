package com.chetan.orderdelivery.presentation.admin.food.addfood

data class AddFoodState(
    val foodName : String = "",
    val foodDetails: String = "",
    val foodPrice: String = "",
    val foodDiscountPrice : String = ""
)

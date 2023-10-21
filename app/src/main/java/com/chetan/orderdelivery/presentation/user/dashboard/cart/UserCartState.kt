package com.chetan.orderdelivery.presentation.user.dashboard.cart

import com.chetan.orderdelivery.domain.model.AllFoods

data class UserCartState(
    val test: String = "",
    val cartItemList: List<AllFoods> = emptyList()

)

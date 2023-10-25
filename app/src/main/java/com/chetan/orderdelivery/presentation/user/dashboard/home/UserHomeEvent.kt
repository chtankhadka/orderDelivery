package com.chetan.orderdelivery.presentation.user.dashboard.home

sealed interface UserHomeEvent{
    data object DismissInfoMsg: UserHomeEvent
    data object AddToCart: UserHomeEvent
    data class SetFavourite(val foodId: String, val isFav: Boolean) : UserHomeEvent
    data object More : UserHomeEvent
}
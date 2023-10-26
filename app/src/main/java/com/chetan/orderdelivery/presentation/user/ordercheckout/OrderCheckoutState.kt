package com.chetan.orderdelivery.presentation.user.ordercheckout

import com.chetan.orderdelivery.data.model.order.RequestFoodOrder
import com.chetan.orderdelivery.domain.model.CheckoutFoods
import com.google.android.gms.maps.model.LatLng

data class OrderCheckoutState(
    val test: String = "",
    val locationAddress : String = "",
    val location : String = "",
    val momobarLok: LatLng = LatLng(
        28.0594641, 81.617649
    ),
    val cameraLocation: LatLng = LatLng(28.0594641, 81.617649,),
    val distance: String ="",
    val orderList: List<CheckoutFoods> = emptyList()
)

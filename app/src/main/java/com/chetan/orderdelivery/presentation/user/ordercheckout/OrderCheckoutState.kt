package com.chetan.orderdelivery.presentation.user.ordercheckout

import com.chetan.orderdelivery.data.model.order.RequestFoodOrder
import com.google.android.gms.maps.model.LatLng

data class OrderCheckoutState(
    val test: String = "",
    val locationAddress : String = "",
    val location : String = "",
    val cameraLocation: LatLng = LatLng(0.0,0.0),
    val orderList: List<RequestFoodOrder> = emptyList()
)

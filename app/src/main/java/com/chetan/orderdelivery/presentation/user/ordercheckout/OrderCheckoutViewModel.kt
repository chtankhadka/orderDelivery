package com.chetan.orderdelivery.presentation.user.ordercheckout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chetan.orderdelivery.data.Resource
import com.chetan.orderdelivery.data.local.Preference
import com.chetan.orderdelivery.data.model.RealtimeModelResponse
import com.chetan.orderdelivery.data.model.SetLatLng
import com.chetan.orderdelivery.data.model.order.RequestFoodOrder
import com.chetan.orderdelivery.domain.use_cases.db.DBUseCases
import com.chetan.orderdelivery.domain.use_cases.firestore.FirestoreUseCases
import com.chetan.orderdelivery.domain.use_cases.realtime.RealtimeUseCases
import com.chetan.orderdelivery.presentation.common.utils.GenerateRandomNumber
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderCheckoutViewModel @Inject constructor(
    private val firestoreUseCases: FirestoreUseCases,
    private val dbUseCases: DBUseCases,
    private val realtimeUseCases: RealtimeUseCases,
    private val preference: Preference
) : ViewModel() {
    private val _state = MutableStateFlow(OrderCheckoutState())
    val state: StateFlow<OrderCheckoutState> = _state

    init {
        getOrderList()
        _state.update {
            it.copy(
                cameraLocation = LatLng(
                    28.0594641, 81.617649,
                )
            )
        }
    }

    private fun getOrderList() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    orderList = dbUseCases.getAllCheckoutFoods()
                )
            }
        }
    }

    val onEvent: (event: OrderCheckoutEvent) -> Unit = { event ->
        viewModelScope.launch {
            when (event) {
                is OrderCheckoutEvent.Location -> {
                    _state.update {
                        it.copy(
                            location = event.value
                        )
                    }
                }

                is OrderCheckoutEvent.LocationAddress -> {
                    _state.update {
                        it.copy(
                            locationAddress = event.value
                        )
                    }
                }

                OrderCheckoutEvent.OrderNow -> {
                    val location = state.value.location.split(",")
                    val setAddress = firestoreUseCases.setAddress(
                        SetLatLng(
                            locationLat = location.first(),
                            locationLng = location.last(),
                            locationAddress = state.value.locationAddress,
                            userName = "",
                            userContactNo = "",
                            userMail = preference.tableName!!,
                            googleProfileUrl = preference.gmailProfile ?: "",
                            dbProfileUrl = "",
                            googleUserName = preference.userName ?: "",
                        )
                    )
                    when (setAddress) {
                        is Resource.Failure -> {}
                        Resource.Loading -> {

                        }

                        is Resource.Success -> {
                            if (setAddress.data) {
                                val orderRequest =
                                    firestoreUseCases.orderFood(data = RequestFoodOrder(orderId = GenerateRandomNumber.generateRandomNumber(
                                        11111111..99999999
                                    ).toString(),
                                        locationLat = location.first(),
                                        locationLng = location.last(),
                                        userName = "",
                                        userContactNo = "",
                                        userMail = preference.tableName!!,
                                        googleProfileUrl = preference.gmailProfile ?: "",
                                        dbProfileUrl = "",
                                        googleUserName = preference.userName ?: "",
                                        locationAddress = state.value.locationAddress,
                                        orderList = state.value.orderList.map { food ->
                                            RequestFoodOrder.OrderedList(
                                                foodId = food.foodId,
                                                foodType = food.foodType,
                                                foodFamily = food.foodFamily,
                                                foodName = food.foodName,
                                                foodDetails = food.foodDetails,
                                                foodPrice = food.foodPrice,
                                                foodDiscount = food.foodDiscount,
                                                foodNewPrice = food.foodNewPrice,
                                                isSelected = food.isSelected,
                                                foodRating = food.foodRating,
                                                newFoodRating = food.newFoodRating,
                                                quantity = food.quantity,
                                                date = food.date,
                                                faceImgName = food.faceImgName,
                                                faceImgUrl = food.faceImgUrl,
                                            )
                                        }))
                                when (orderRequest) {
                                    is Resource.Failure -> {

                                    }

                                    Resource.Loading -> {

                                    }

                                    is Resource.Success -> {
                                        realtimeUseCases.insert(
                                            RealtimeModelResponse.RealTimeNewOrderRequest(
                                                true, ""
                                            )
                                        )
                                        for (food in state.value.orderList) {
                                            if (food.isSelected) {
                                                firestoreUseCases.deleteCartItem(food.foodId)
                                            }
                                        }
                                    }
                                }
                            }

                        }
                    }

                }

            }
        }
    }
}
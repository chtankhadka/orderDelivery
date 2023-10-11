package com.chetan.orderdelivery.presentation.user.ordercheckout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chetan.orderdelivery.data.Resource
import com.chetan.orderdelivery.data.model.order.RequestFoodOrder
import com.chetan.orderdelivery.domain.use_cases.firestore.FirestoreUseCases
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderCheckoutViewModel @Inject constructor(
    private val firestoreUseCases: FirestoreUseCases
): ViewModel() {
    private val _state = MutableStateFlow(OrderCheckoutState())
    val state : StateFlow<OrderCheckoutState> = _state

    init {
        _state.update {
            it.copy(
                cameraLocation = LatLng(
                    28.0594641,81.617649,
                )
            )
        }
    }

    val onEvent : (event: OrderCheckoutEvent) -> Unit = {event ->
        viewModelScope.launch{
            when(event) {

                is OrderCheckoutEvent.Location -> {
                    _state.update {
                        it.copy(
                            location = event.value
                        )
                    }
                }
                is OrderCheckoutEvent.LocationAddress ->{
                    _state.update {
                        it.copy(
                            locationAddress = event.value
                        )
                    }
                }
                OrderCheckoutEvent.OrderNow -> {
                    firestoreUseCases.orderFood(data = listOf(
                        RequestFoodOrder(
                            location = state.value.location,
                            locationAddress = state.value.locationAddress
                        )
                    ))
                }


            }
        }
    }
}
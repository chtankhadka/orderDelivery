package com.chetan.orderdelivery.presentation.user.ordercheckout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chetan.orderdelivery.domain.use_cases.firestore.FirestoreUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderCheckoutViewModel @Inject constructor(
    private val firestoreUseCases: FirestoreUseCases
): ViewModel() {
    private val _state = MutableStateFlow(OrderCheckoutState())
    val state : StateFlow<OrderCheckoutState> = _state

    val onEvent : (event: OrderCheckoutEvent) -> Unit = {event ->
        viewModelScope.launch{
            when(event) {
                OrderCheckoutEvent.test -> {

                }
            }
        }
    }
}
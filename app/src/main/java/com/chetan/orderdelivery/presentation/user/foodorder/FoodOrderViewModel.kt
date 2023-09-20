package com.chetan.orderdelivery.presentation.user.foodorder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chetan.orderdelivery.domain.use_cases.firestore.FirestoreUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodOrderViewModel @Inject constructor(
    private val firestoreUseCases: FirestoreUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(FoodOrderState())
    val state: StateFlow<FoodOrderState> = _state

    val onEvent: (event: FoodOrderEvent) -> Unit = { event ->
        viewModelScope.launch {
            when (event) {
                is FoodOrderEvent.OrderFood -> {
                    firestoreUseCases.orderFood(event.value)
                }
            }
        }
    }
}
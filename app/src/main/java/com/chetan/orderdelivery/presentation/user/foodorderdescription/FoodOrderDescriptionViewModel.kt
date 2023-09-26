package com.chetan.orderdelivery.presentation.user.foodorderdescription

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chetan.orderdelivery.domain.use_cases.firestore.FirestoreUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodOrderDescriptionViewModel @Inject constructor(
    private val firestoreUseCases: FirestoreUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(FoodOrderDescriptionState())
    val state: StateFlow<FoodOrderDescriptionState> = _state

    val onEvent: (event: FoodOrderDescriptionEvent) -> Unit = { event ->
        viewModelScope.launch {
            when (event) {
                is FoodOrderDescriptionEvent.OrderFood -> {
                    firestoreUseCases.orderFood(event.value)
                }
            }
        }
    }
}
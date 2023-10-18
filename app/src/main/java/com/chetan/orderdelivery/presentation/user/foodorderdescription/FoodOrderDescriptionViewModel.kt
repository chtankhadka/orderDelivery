package com.chetan.orderdelivery.presentation.user.foodorderdescription

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chetan.orderdelivery.R
import com.chetan.orderdelivery.data.Resource
import com.chetan.orderdelivery.domain.use_cases.firestore.FirestoreUseCases
import com.chetan.orderdelivery.presentation.common.components.dialogs.Message
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
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
                is FoodOrderDescriptionEvent.GetFoodItemDetails -> {
                    _state.update {
                        it.copy(
                            infoMsg = Message.Loading(lottieImage = R.raw.loading_food, title = "Loading", description = "Fetching Details...", yesNoRequired = false, isCancellable = false)
                        )
                    }
                    when(val foodItemDetailsResponse = firestoreUseCases.getFoodItem(event.value)){
                        is Resource.Failure -> {

                        }
                        Resource.Loading -> {

                        }
                        is Resource.Success -> {
                            _state.update {
                                it.copy(
                                    foodItemDetails = foodItemDetailsResponse.data,
                                    infoMsg = null,
                                )
                            }
                        }
                    }
                }
                is FoodOrderDescriptionEvent.OrderFood -> {

                }


            }
        }
    }
}
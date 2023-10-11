package com.chetan.orderdelivery.presentation.admin.food.addfood

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AddFoodViewModel @Inject constructor(

) : ViewModel(){

    private val _state = MutableStateFlow(AddFoodState())
    val state: StateFlow<AddFoodState> = _state


    val onEvent : (event : AddFoodEvent) -> Unit = {event ->
        when(event){
            is AddFoodEvent.OnFoodNameChange -> {
                _state.update {
                    it.copy(
                        foodName = event.value
                    )
                }
            }

            is AddFoodEvent.OnFoodDetailsChange -> {
                _state.update {
                    it.copy(
                        foodDetails = event.value
                    )
                }
            }
        }
    }
}
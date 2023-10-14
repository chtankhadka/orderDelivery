package com.chetan.orderdelivery.presentation.admin.food.ratingUpdate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chetan.orderdelivery.data.Resource
import com.chetan.orderdelivery.domain.repository.FirestoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RatingUpdateViewModel @Inject constructor(
    private val repository: FirestoreRepository
) : ViewModel() {

    private val _state = MutableStateFlow(RatingUpdateState())
    val state : StateFlow<RatingUpdateState> = _state

    init {
        getFoods()
    }


    val onEvent : (event : RatingUpdateEvent) -> Unit = {event ->
        viewModelScope.launch {
            when(event){
                is RatingUpdateEvent.UpdateThis -> {

                }
            }
        }
    }


    private fun getFoods(){
        viewModelScope.launch {
            val foodList = repository.getFoodsForUpdate()
            when(foodList){
                is Resource.Failure -> {

                }
                Resource.Loading -> {

                }
                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            foodList = foodList.data
                        )
                    }
                }
            }
        }
    }

}
package com.chetan.orderdelivery.presentation.user.dashboard.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chetan.orderdelivery.data.Resource
import com.chetan.orderdelivery.data.model.RealtimeModelResponse
import com.chetan.orderdelivery.domain.use_cases.firestore.FirestoreUseCases
import com.chetan.orderdelivery.domain.use_cases.realtime.RealtimeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserHomeViewModel @Inject constructor(
    private val firestoreUseCases: FirestoreUseCases,
    private val realtimeUseCases: RealtimeUseCases
) : ViewModel(){
    private val _state = MutableStateFlow(UserHomeState())
    val state: StateFlow<UserHomeState> = _state

    init {
        viewModelScope.launch {
            realtimeUseCases.getItems().collect{data ->
                when(data){
                    is Resource.Failure -> {

                    }
                    Resource.Loading -> {

                    }
                    is Resource.Success -> {
                        _state.update { it.copy(name = data.data) }
                    }
                }
                println("_____________________________________________")
            }
        }
    }




    val onEvent : (event: UserHomeEvent) -> Unit ={ event ->
        viewModelScope.launch {
            when(event){
                UserHomeEvent.AddToCart -> {

                }

                UserHomeEvent.More -> {
                    realtimeUseCases.insert(RealtimeModelResponse.RealtimeItems("dd","dd"))
                }
            }
        }

    }

}
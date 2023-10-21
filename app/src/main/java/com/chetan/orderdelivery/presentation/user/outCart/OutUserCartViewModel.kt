package com.chetan.orderdelivery.presentation.user.outCart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chetan.orderdelivery.domain.use_cases.firestore.FirestoreUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OutUserCartViewModel @Inject constructor(
    private val firestoreUseCases: FirestoreUseCases
) :ViewModel(){
    private val _state = MutableStateFlow(OutUserCartState())
    val state : StateFlow<OutUserCartState> = _state

    val onEvent : (event: OutUserCartEvent) -> Unit = {event ->
        viewModelScope.launch {
            when(event){
                OutUserCartEvent.test -> {

                }
            }
        }
    }
}
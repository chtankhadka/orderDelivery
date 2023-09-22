package com.chetan.orderdelivery.presentation.user.dashboard.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chetan.orderdelivery.domain.use_cases.firestore.FirestoreUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserHomeViewModel @Inject constructor(
    private val firestoreUseCases: FirestoreUseCases
) : ViewModel(){
    private val _state = MutableStateFlow(UserHomeState())
    val state: StateFlow<UserHomeState> = _state


    val onEvent : (event: UserHomeEvent) -> Unit ={ event ->
        viewModelScope.launch {
            when(event){
                UserHomeEvent.AddToCart -> {

                }
            }
        }

    }

}
package com.chetan.orderdelivery.presentation.user.dashboard.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chetan.orderdelivery.domain.use_cases.firestore.FirestoreUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserHistoryViewModel @Inject constructor(
    private val firestoreUseCases: FirestoreUseCases
) : ViewModel(){
    private val _state = MutableStateFlow(UserHistoryState())
    val state : StateFlow<UserHistoryState> = _state

    val onEvent : (event: UserHistoryEvent) -> Unit = {event ->
        viewModelScope.launch {
            when(event){
                UserHistoryEvent.RateIt -> {

                }
            }
        }

    }
}
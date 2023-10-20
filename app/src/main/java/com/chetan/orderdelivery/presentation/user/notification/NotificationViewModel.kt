package com.chetan.orderdelivery.presentation.user.notification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chetan.orderdelivery.domain.repository.FirestoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val repository: FirestoreRepository
): ViewModel(){

    private val _state = MutableStateFlow(NotificationState())
    val state : StateFlow<NotificationState> = _state

    init {

    }

    val onEvent: (event: NotificationEvent) -> Unit = { event ->
            viewModelScope.launch {
                when(event){
                    NotificationEvent.Test -> {

                    }
                }
            }
    }
}
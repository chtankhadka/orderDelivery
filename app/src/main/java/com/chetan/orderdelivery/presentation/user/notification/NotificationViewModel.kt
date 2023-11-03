package com.chetan.orderdelivery.presentation.user.notification

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
class NotificationViewModel @Inject constructor(
    private val repository: FirestoreRepository
): ViewModel(){

    private val _state = MutableStateFlow(NotificationState())
    val state : StateFlow<NotificationState> = _state

    init {
        notification()
    }
    fun notification(){
        viewModelScope.launch {
            val notificationList = repository.getNotification()
            when(notificationList){
                is Resource.Failure -> {

                }
                Resource.Loading -> {

                }
                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            notificationList = notificationList.data
                        )
                    }
                }
            }
        }
    }

    val onEvent: (event: NotificationEvent) -> Unit = { event ->
            viewModelScope.launch {
                when(event){
                    NotificationEvent.Test -> {

                    }

                    is NotificationEvent.DeleteNotification -> {
                        val delete = repository.deleteNotification(event.id)
                        when(delete){
                            is Resource.Failure -> {

                            }
                            Resource.Loading -> {

                            }
                            is Resource.Success -> {
                                _state.update {
                                    it.copy(
                                        notificationList = state.value.notificationList.filter { it.time != event.id }
                                    )
                                }
                            }
                        }

                    }
                }
            }
    }
}
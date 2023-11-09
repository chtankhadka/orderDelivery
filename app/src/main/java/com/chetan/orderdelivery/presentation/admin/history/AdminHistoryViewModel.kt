package com.chetan.orderdelivery.presentation.admin.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chetan.orderdelivery.data.Resource
import com.chetan.orderdelivery.domain.use_cases.firestore.FirestoreUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminHistoryViewModel @Inject constructor(
    private val firestoreUseCases: FirestoreUseCases
): ViewModel(){

    private val _state = MutableStateFlow(AdminHistoryState())
    val state : StateFlow<AdminHistoryState> = _state
    init {
        viewModelScope.launch {
            val historyList = firestoreUseCases.getAdminHistories()
            when(historyList){
                is Resource.Failure -> {

                }
                Resource.Loading -> {

                }
                is Resource.Success -> {

                }
            }
        }
    }
    val onEvent : (event: AdminHistoryEvent) -> Unit = {event ->
        viewModelScope.launch {
            when(event){
                AdminHistoryEvent.DeleteHistory -> {

                }
            }
        }
    }



}
package com.chetan.orderdelivery.presentation.admin.dashboard.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chetan.orderdelivery.data.Resource
import com.chetan.orderdelivery.domain.repository.FirestoreRepository
import com.chetan.orderdelivery.domain.use_cases.firestore.FirestoreUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminHomeViewModel @Inject constructor(
    private val firestoreUseCases: FirestoreUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(AdminHomeState())
    val state : StateFlow<AdminHomeState> = _state

    init {
        getOrders()
    }

    private fun getOrders(){
        viewModelScope.launch {
            val orderList = firestoreUseCases.getFoodOrders()
            when(orderList){
                is Resource.Failure -> {}
                Resource.Loading -> {

                }
                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            orderList = orderList.data
                        )
                    }
                }
            }
        }
    }

    val onEvent : (event: AdminHomeEvent) -> Unit = { event ->
        viewModelScope.launch {
            when(event){
                AdminHomeEvent.Test -> {

                }

                AdminHomeEvent.DismissInfoMsg -> {
                    _state.update {
                        it.copy(
                            infoMsg = null
                        )
                    }
                }
            }
        }
    }

}
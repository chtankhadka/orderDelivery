package com.chetan.orderdelivery.presentation.admin.orderdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chetan.orderdelivery.data.Resource
import com.chetan.orderdelivery.domain.use_cases.firestore.FirestoreUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminOrderDetailViewModel @Inject constructor(
    private val firestoreUseCases: FirestoreUseCases
): ViewModel() {
    private val _state = MutableStateFlow(AdminOrderDetailState())
    val state : StateFlow<AdminOrderDetailState> = _state

    init {

    }
    val onEvent : (event : AdminOrderDetailEvent) -> Unit = {event ->
        viewModelScope.launch {
            when(event){
                is AdminOrderDetailEvent.Delivered -> {

                }

                AdminOrderDetailEvent.DismissInfoMsg -> {

                }

                is AdminOrderDetailEvent.GetOrderDetails -> {
                    val orderDetails = firestoreUseCases.getFoodOrderDetails(event.user)
                    when(orderDetails){
                        is Resource.Failure -> {

                        }
                        Resource.Loading -> {

                        }
                        is Resource.Success -> {
                            _state.update {
                                it.copy(
                                    orderDetails = orderDetails.data
                                )
                            }
                        }
                    }

                }
            }
        }
    }
}
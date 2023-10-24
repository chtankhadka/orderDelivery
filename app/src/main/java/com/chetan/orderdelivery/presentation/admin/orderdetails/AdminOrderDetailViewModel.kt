package com.chetan.orderdelivery.presentation.admin.orderdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chetan.orderdelivery.R
import com.chetan.orderdelivery.data.Resource
import com.chetan.orderdelivery.domain.use_cases.firestore.FirestoreUseCases
import com.chetan.orderdelivery.presentation.common.components.dialogs.Message
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
                    _state.update {
                        it.copy(
                            infoMsg = Message.Loading(
                                title = "Updating",
                                description = "Please wait...",
                                lottieImage = R.raw.loading_food,
                                yesNoRequired = false,
                                isCancellable = false
                            )
                        )
                    }

                    val orderIdDetails = state.value.orderDetails.find { it.orderId == event.value }!!
                    val updateUserHistory = firestoreUseCases.updateUserHistory(data = orderIdDetails)
                    when(updateUserHistory){
                        is Resource.Failure -> {}
                        Resource.Loading -> {

                        }
                        is Resource.Success -> {
                            if (updateUserHistory.data){
                                val updateDeliverHistory = firestoreUseCases.updateDeliveredHistory(data = orderIdDetails)
                                when(updateDeliverHistory){
                                    is Resource.Failure -> {}
                                    Resource.Loading -> {

                                    }
                                    is Resource.Success -> {
                                        if (updateDeliverHistory.data){
                                            val orderDelivereds = firestoreUseCases.orderDelivered(data = orderIdDetails)
                                            when(orderDelivereds){
                                                is Resource.Failure -> {}
                                                Resource.Loading -> {

                                                }
                                                is Resource.Success -> {
                                                    if (orderDelivereds.data){
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
                                }
                            }
                        }
                    }
                }

                AdminOrderDetailEvent.DismissInfoMsg -> {
                    _state.update {
                        it.copy(
                            infoMsg = null
                        )
                    }
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
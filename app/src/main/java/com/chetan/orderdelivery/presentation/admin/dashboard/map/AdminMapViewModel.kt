package com.chetan.orderdelivery.presentation.admin.dashboard.map

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
class AdminMapViewModel @Inject constructor(
    private val firestoreUseCases: FirestoreUseCases
): ViewModel(){
    private val _state = MutableStateFlow(AdminMapState())
    val state : StateFlow<AdminMapState> =_state

    init {

        getFoodOrders()
    }
    fun getFoodOrders(){
        viewModelScope.launch {
            when(val resource = firestoreUseCases.getFoodOrders()){
                is Resource.Failure -> {

                }
                Resource.Loading -> {

                }
                is Resource.Success -> {
                    println(resource.data.toString())
                    _state.update {
                        it.copy(
                            orderedUserList = resource.data
                        )
                    }
                }
            }
        }
    }

    val onEvent :(event:  AdminMapEvent) -> Unit = {event ->
        viewModelScope.launch {
            when(event){

                else -> {}
            }
        }
    }
}
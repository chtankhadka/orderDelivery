package com.chetan.orderdelivery.presentation.admin.addoffer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminAddOfferViewModel @Inject constructor(

) : ViewModel(){
    private val _state = MutableStateFlow(AdminAddOfferState())
    val state: StateFlow<AdminAddOfferState> = _state

    init {


    }

    val onEvent: (event: AdminAddOfferEvent ) -> Unit = {event ->
        viewModelScope.launch{
            when(event){

                else -> {

                }
            }
        }
    }
}
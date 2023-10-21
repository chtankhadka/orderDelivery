package com.chetan.orderdelivery.presentation.user.dashboard.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chetan.orderdelivery.data.Resource
import com.chetan.orderdelivery.data.model.GetCartItemModel
import com.chetan.orderdelivery.domain.model.AllFoods
import com.chetan.orderdelivery.domain.repository.DBRepository
import com.chetan.orderdelivery.domain.use_cases.firestore.FirestoreUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserCartViewModel @Inject constructor(
    private val firestoreUseCases: FirestoreUseCases,
    private val dbRepository: DBRepository
) :ViewModel(){
    private val _state = MutableStateFlow(UserCartState())
    val state : StateFlow<UserCartState> = _state

    init {
        getCartItems()
    }

    fun getCartItems(){
        viewModelScope.launch {
            val allFoods = dbRepository.getAllFoods()

            when(val cartItemList = firestoreUseCases.getCartItems()){
                is Resource.Failure -> {

                }
                Resource.Loading -> {

                }
                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            cartItemList = allFoods.map { food ->
                                val updatedQuantity =cartItemList.data.find { it.foodId == food.foodId }?.foodQuantity ?: food.quantity
                                food.copy(quantity = updatedQuantity)
                            }
                        )
                    }
                }
            }
        }
    }

    val onEvent : (event: UserCartEvent) -> Unit = {event ->
        viewModelScope.launch {
            when(event){
                UserCartEvent.test -> {

                }
            }
        }
    }
}
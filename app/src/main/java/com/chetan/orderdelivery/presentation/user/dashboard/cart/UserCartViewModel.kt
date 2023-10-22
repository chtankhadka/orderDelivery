package com.chetan.orderdelivery.presentation.user.dashboard.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chetan.orderdelivery.R
import com.chetan.orderdelivery.data.Resource
import com.chetan.orderdelivery.data.model.GetCartItemModel
import com.chetan.orderdelivery.domain.model.AllFoods
import com.chetan.orderdelivery.domain.model.CheckoutFoods
import com.chetan.orderdelivery.domain.repository.DBRepository
import com.chetan.orderdelivery.domain.use_cases.db.DBUseCases
import com.chetan.orderdelivery.domain.use_cases.firestore.FirestoreUseCases
import com.chetan.orderdelivery.presentation.common.components.dialogs.Message
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserCartViewModel @Inject constructor(
    private val firestoreUseCases: FirestoreUseCases, private val dbUseCases: DBUseCases
) : ViewModel() {
    private val _state = MutableStateFlow(UserCartState())
    val state: StateFlow<UserCartState> = _state

    init {
        getCartItems()
    }

    private fun getCartItems() {
        viewModelScope.launch {
            dbUseCases.removeAllCheckoutFoods()
            val allFoods = dbUseCases.getAllFoods()

            when (val cartItemList = firestoreUseCases.getCartItems()) {
                is Resource.Failure -> {

                }

                Resource.Loading -> {

                }

                is Resource.Success -> {
                    println(cartItemList)
                    _state.update {
                        it.copy(cartItemList = allFoods.filter { food ->
                            cartItemList.data.any { it.foodId == food.foodId }
                        }.map { food ->
                            val updatedQuantity =
                                cartItemList.data.find { it.foodId == food.foodId }?.foodQuantity
                                    ?: food.quantity
                            food.copy(quantity = updatedQuantity)
                        }.toMutableList()
                        )
                    }
                }
            }
        }
    }

    val onEvent: (event: UserCartEvent) -> Unit = { event ->
        viewModelScope.launch {
            when (event) {
                UserCartEvent.DismissInfoMsg -> {
                    _state.update {
                        it.copy(
                            infoMsg = null
                        )
                    }
                }

                UserCartEvent.DeleteItems -> {
                    _state.update {
                        it.copy(
                            infoMsg = Message.Loading(
                                title = "Deleting Item",
                                description = "Please wait while deleting...",
                                lottieImage = R.raw.delete,
                                yesNoRequired = false,
                                isCancellable = false
                            )
                        )
                    }
                    for (food in state.value.cartItemList) {
                        if (food.isSelected) {
                            firestoreUseCases.deleteCartItem(food.foodId)
                        }
                    }
                    _state.update {
                        it.copy(
                            infoMsg = null
                        )
                    }
                    getCartItems()

                }

                is UserCartEvent.DecreaseQuantity -> {
                    _state.update {
                        it.copy(cartItemList = state.value.cartItemList.map { food ->
                            if (food.foodId == event.foodId) {
                                food.copy(quantity = event.lastValue - 1)
                            } else {
                                food
                            }
                        })
                    }
                }

                is UserCartEvent.IncreaseQuantity -> {
                    _state.update {
                        it.copy(cartItemList = state.value.cartItemList.map { food ->
                            if (food.foodId == event.foodId) {
                                food.copy(quantity = event.lastValue + 1)
                            } else {
                                food
                            }
                        })
                    }
                }

                is UserCartEvent.SelectAllCheckBox -> {
                    _state.update {
                        it.copy(
                            cartItemList = state.value.cartItemList.map { it.copy(isSelected = event.isChecked) },
                        )
                    }
                }

                is UserCartEvent.ItemSelected -> {
                    _state.update {
                        it.copy(cartItemList = state.value.cartItemList.map { food ->
                            if (food.foodId == event.item) {
                                food.copy(isSelected = event.isItemSelected)
                            } else {
                                food
                            }
                        })
                    }
                }

                UserCartEvent.Checkout -> {
                    dbUseCases.removeAllCheckoutFoods()
                    dbUseCases.insertAllCheckoutFoodList(checkList = state.value.cartItemList.filter { it.isSelected }
                        .map {
                            CheckoutFoods(
                                foodId = it.foodId,
                                foodType = it.foodType,
                                foodFamily = it.foodFamily,
                                foodName = it.foodName,
                                foodDetails = it.foodDetails,
                                foodPrice = it.foodPrice,
                                foodDiscount = it.foodDiscount,
                                foodNewPrice = it.foodNewPrice,
                                isSelected = it.isSelected,
                                foodRating = it.foodRating,
                                newFoodRating = it.newFoodRating,
                                quantity = it.quantity,
                                date = it.date,
                                faceImgName = it.faceImgName,
                                faceImgUrl = it.faceImgUrl
                            )
                        })
                    println(dbUseCases.getAllCheckoutFoods)
                }
            }
        }
    }
}
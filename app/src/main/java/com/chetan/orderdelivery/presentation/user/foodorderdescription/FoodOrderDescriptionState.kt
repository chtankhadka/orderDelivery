package com.chetan.orderdelivery.presentation.user.foodorderdescription

import com.chetan.orderdelivery.data.model.GetFoodResponse
import com.chetan.orderdelivery.domain.model.AllFoods
import com.chetan.orderdelivery.presentation.common.components.OrderDeliveryScreenState
import com.chetan.orderdelivery.presentation.common.components.dialogs.Message

data class FoodOrderDescriptionState(
    val location : String = "",
    val foodItemDetails : AllFoods = AllFoods(),
    val foodPrice: Int = 0,
    val foodDiscount: Int = 0,
    val foodQuantity: Int = 1,
    val totalCartItem : Int = 0,
    override val infoMsg: Message? = null
): OrderDeliveryScreenState(infoMsg)

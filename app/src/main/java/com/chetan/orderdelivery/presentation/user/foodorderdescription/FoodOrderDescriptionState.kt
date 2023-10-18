package com.chetan.orderdelivery.presentation.user.foodorderdescription

import com.chetan.orderdelivery.data.model.GetFoodResponse
import com.chetan.orderdelivery.presentation.common.components.OrderDeliveryScreenState
import com.chetan.orderdelivery.presentation.common.components.dialogs.Message

data class FoodOrderDescriptionState(
    val location : String = "",
    val foodItemDetails : GetFoodResponse = GetFoodResponse(),
    override val infoMsg: Message? = null
): OrderDeliveryScreenState(infoMsg)

package com.chetan.orderdelivery.domain.repository

import com.chetan.orderdelivery.data.Resource
import com.chetan.orderdelivery.domain.model.AllFoods
import kotlinx.coroutines.flow.Flow

interface DBRepository {
    suspend fun getAllFoods(): List<AllFoods>
    suspend fun insertFoodList(noteList: List<AllFoods>)
}
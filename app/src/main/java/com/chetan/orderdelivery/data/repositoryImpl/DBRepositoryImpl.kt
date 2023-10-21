package com.chetan.orderdelivery.data.repositoryImpl

import com.chetan.orderdelivery.data.Resource
import com.chetan.orderdelivery.data.data_source.AllFoodsDao
import com.chetan.orderdelivery.domain.model.AllFoods
import com.chetan.orderdelivery.domain.repository.DBRepository
import kotlinx.coroutines.flow.Flow

class DBRepositoryImpl(
    private val dao: AllFoodsDao
) : DBRepository{
    override suspend fun getAllFoods(): List<AllFoods> {
        return dao.getAllFoods()
    }

    override suspend fun insertFoodList(noteList: List<AllFoods>) {
        dao.insertFoodList(noteList)
    }
}
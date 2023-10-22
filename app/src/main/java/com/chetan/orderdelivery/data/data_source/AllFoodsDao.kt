package com.chetan.orderdelivery.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chetan.orderdelivery.data.Resource
import com.chetan.orderdelivery.domain.model.AllFoods
import com.chetan.orderdelivery.domain.model.CheckoutFoods
import kotlinx.coroutines.flow.Flow


@Dao
interface AllFoodsDao {

    @Query("SELECT * FROM allFoods")
    suspend fun getAllFoods() : List<AllFoods>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFoodList(noteList: List<AllFoods>)

    @Query("SELECT * FROM CheckoutFoods")
    suspend fun getAllCheckoutFoods() : List<CheckoutFoods>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCheckedoutFoods(checkedList: List<CheckoutFoods>)

    @Query("DELETE FROM CheckoutFoods")
    suspend fun removeAllCheckoutFoods()

}
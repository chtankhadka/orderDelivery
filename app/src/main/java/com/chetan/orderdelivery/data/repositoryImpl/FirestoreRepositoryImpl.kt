package com.chetan.orderdelivery.data.repositoryImpl

import com.chetan.orderdelivery.data.Resource
import com.chetan.orderdelivery.data.model.order.GetFoodOrder
import com.chetan.orderdelivery.data.model.order.RequestFoodOrder
import com.chetan.orderdelivery.domain.repository.FirestoreRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FirestoreRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : FirestoreRepository {
    override suspend fun orderFood(data: List<RequestFoodOrder>): Resource<Any> {
        return try {
            withContext(Dispatchers.IO){
                data.map { order ->
                    async {
                        firestore.collection("users")
                            .document("chetan")
                            .set(order)
                            .await()
                    }
                }.awaitAll()
            }

            Resource.Success(Unit)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Failure(e)
        }
    }

    override suspend fun getFoodOrders(): Resource<List<GetFoodOrder>> {
        return try {
            val orderList = mutableListOf<GetFoodOrder>()
            val querySnapshot = firestore.collection("users").get().await()
            for (document in querySnapshot.documents) {
                val order = document.toObject<GetFoodOrder>()
                order?.let {
                    orderList.add(it)
                }
            }
            Resource.Success(orderList)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Failure(e)
        }
    }
}
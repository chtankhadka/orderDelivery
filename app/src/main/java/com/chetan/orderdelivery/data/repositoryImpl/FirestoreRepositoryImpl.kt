package com.chetan.orderdelivery.data.repositoryImpl

import com.chetan.orderdelivery.data.Resource
import com.chetan.orderdelivery.data.model.order.GetFoodOrder
import com.chetan.orderdelivery.domain.repository.FirestoreRepository
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirestoreRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : FirestoreRepository {
    override suspend fun orderFood(location: String): Boolean {
        var isSuccess = false
        return try {
            val firestoreQuery =
                firestore.collection("users")
                    .document("chetan").set(mapOf("location" to location))
                    .addOnSuccessListener {
                        isSuccess = true
                    }.addOnFailureListener {
                        isSuccess = false
                    }
            isSuccess
        } catch (e: Exception) {
            e.printStackTrace()
            isSuccess
        }
    }

    override suspend fun getFoodOrders(): Resource<List<GetFoodOrder>> {
        return try {
            val orderList = mutableListOf<GetFoodOrder>()
            val querySnapshot = firestore.collection("users").get().await()
            for (document in querySnapshot.documents){
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
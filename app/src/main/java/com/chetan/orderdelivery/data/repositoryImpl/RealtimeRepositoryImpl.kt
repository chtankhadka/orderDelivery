package com.chetan.orderdelivery.data.repositoryImpl

import com.chetan.orderdelivery.data.Resource
import com.chetan.orderdelivery.data.model.RealtimeModelResponse
import com.chetan.orderdelivery.domain.repository.RealtimeRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RealtimeRepositoryImpl @Inject constructor(
    private val realtime: FirebaseDatabase
) : RealtimeRepository {
    override suspend fun insert(item: RealtimeModelResponse.RealtimeItems): Resource<String> {

        return try {
            val db = realtime.getReference("nep").push().setValue(item).await()

            Resource.Success("")
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Failure(e)
        }
    }

    override fun getItems(): Flow<Resource<List<RealtimeModelResponse>>> = callbackFlow {
                val valueEvent = object : ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val items = snapshot.children.map {
                            RealtimeModelResponse(
                                item = it.getValue(RealtimeModelResponse.RealtimeItems::class.java)?:RealtimeModelResponse.RealtimeItems("empty",""),
                                key = it.key?:""
                            )
                        }
                        trySend(Resource.Success(items))
                        println(items)
                    }
                    override fun onCancelled(error: DatabaseError) {
                        Resource.Failure(error.toException())
                    }

                }
                realtime.getReference("nep").addValueEventListener(valueEvent)
                awaitClose {
                    realtime.getReference("nep").removeEventListener(valueEvent)
                    close()
                }

    }

}
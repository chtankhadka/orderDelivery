package com.chetan.orderdelivery.data.repositoryImpl

import android.net.Uri
import com.chetan.orderdelivery.data.Resource
import com.chetan.orderdelivery.domain.repository.StorageRepository
import com.google.firebase.storage.FirebaseStorage
import javax.inject.Inject

class StorageRepositoryImpl @Inject constructor(
    private val storage : FirebaseStorage
): StorageRepository {
    override suspend fun InsertImage(fileUri: Uri): Resource<Pair<String, String>> {
        return Resource.Success(Pair("",""))
    }
}
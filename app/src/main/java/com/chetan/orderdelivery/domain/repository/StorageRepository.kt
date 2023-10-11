package com.chetan.orderdelivery.domain.repository

import android.net.Uri
import com.chetan.orderdelivery.data.Resource


interface StorageRepository {
    suspend fun InsertImage(fileUri: Uri) : Resource<Pair<String,String>>
}
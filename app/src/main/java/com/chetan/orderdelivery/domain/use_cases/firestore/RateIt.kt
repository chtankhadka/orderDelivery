package com.chetan.orderdelivery.domain.use_cases.firestore

import com.chetan.orderdelivery.data.model.Rating
import com.chetan.orderdelivery.domain.repository.FirestoreRepository
import javax.inject.Inject

class RateIt @Inject constructor(
    private val repository: FirestoreRepository
) {
    suspend operator fun invoke(data: Rating) = repository.rating(data)
}
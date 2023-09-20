package com.chetan.orderdelivery.di

import com.chetan.orderdelivery.data.repositoryImpl.FirestoreRepositoryImpl
import com.chetan.orderdelivery.domain.repository.FirestoreRepository
import com.chetan.orderdelivery.domain.use_cases.firestore.FirestoreUseCases
import com.chetan.orderdelivery.domain.use_cases.firestore.GetFoodOrders
import com.chetan.orderdelivery.domain.use_cases.firestore.OrderFood
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.FirebaseFirestoreKtxRegistrar
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Singleton
    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore{
        return FirebaseFirestore.getInstance()
    }

    @Singleton
    @Provides
    fun provideFirebaseStorage() : FirebaseStorage{
        return FirebaseStorage.getInstance()
    }

    @Singleton
    @Provides
    fun provideFirebaseFirestoreRepository(firestore: FirebaseFirestore): FirestoreRepository{
        return FirestoreRepositoryImpl(firestore)
    }

    @Singleton
    @Provides
    fun provideFirestoreUseCases(repository: FirestoreRepository) =
        FirestoreUseCases(
            orderFood = OrderFood(repository = repository),
            getFoodOrders = GetFoodOrders(repository = repository)
        )
}
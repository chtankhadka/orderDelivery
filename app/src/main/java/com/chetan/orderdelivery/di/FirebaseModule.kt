package com.chetan.orderdelivery.di

import com.chetan.orderdelivery.data.repositoryImpl.FirestoreRepositoryImpl
import com.chetan.orderdelivery.data.repositoryImpl.RealtimeRepositoryImpl
import com.chetan.orderdelivery.data.repositoryImpl.StorageRepositoryImpl
import com.chetan.orderdelivery.domain.repository.FirestoreRepository
import com.chetan.orderdelivery.domain.repository.RealtimeRepository
import com.chetan.orderdelivery.domain.repository.StorageRepository
import com.chetan.orderdelivery.domain.use_cases.firestore.AddFood
import com.chetan.orderdelivery.domain.use_cases.firestore.AddToCart
import com.chetan.orderdelivery.domain.use_cases.firestore.FirestoreUseCases
import com.chetan.orderdelivery.domain.use_cases.firestore.GetCartItems
import com.chetan.orderdelivery.domain.use_cases.firestore.GetFoodItem
import com.chetan.orderdelivery.domain.use_cases.firestore.GetFoodOrders
import com.chetan.orderdelivery.domain.use_cases.firestore.GetFoods
import com.chetan.orderdelivery.domain.use_cases.firestore.GetFoodsForUpdate
import com.chetan.orderdelivery.domain.use_cases.firestore.OrderFood
import com.chetan.orderdelivery.domain.use_cases.firestore.RateIt
import com.chetan.orderdelivery.domain.use_cases.firestore.UpdateRating
import com.chetan.orderdelivery.domain.use_cases.realtime.GetItems
import com.chetan.orderdelivery.domain.use_cases.realtime.Insert
import com.chetan.orderdelivery.domain.use_cases.realtime.RealtimeUseCases
import com.chetan.orderdelivery.domain.use_cases.storage.DeleteImage
import com.chetan.orderdelivery.domain.use_cases.storage.FirestorageUseCases
import com.chetan.orderdelivery.domain.use_cases.storage.InsertImage
import com.google.firebase.database.FirebaseDatabase
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
    fun provideFirebaseStorageRepository(storage: FirebaseStorage) : StorageRepository{
        return StorageRepositoryImpl(storage)
    }
    @Singleton
    @Provides
    fun provideStorageUseCases(repository: StorageRepository) =
        FirestorageUseCases(
            insertImage = InsertImage(repository = repository),
            deleteImage = DeleteImage(repository = repository)
        )

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
            getFoodOrders = GetFoodOrders(repository = repository),
            getFoodItem = GetFoodItem(repository = repository),
            getFoods = GetFoods(repository = repository),
            addFood = AddFood(repository = repository),

            getCartItems =GetCartItems(repository = repository),
            addToCart = AddToCart(repository = repository),

            rateIt = RateIt(repository = repository),
            updateRating = UpdateRating(repository = repository),
            getFoodsForUpdate = GetFoodsForUpdate(repository = repository)
        )

    @Singleton
    @Provides
    fun provideFirebaseRealtime() : FirebaseDatabase{
        return FirebaseDatabase.getInstance()
    }

    @Singleton
    @Provides
    fun provideFirebaseRealtimeRepository(realtime: FirebaseDatabase) : RealtimeRepository{
        return RealtimeRepositoryImpl(realtime)
    }

    @Singleton
    @Provides
    fun provideFirebaseRealtimeUseCases(repository: RealtimeRepository) =
            RealtimeUseCases(
                insert = Insert(realtimeRepository = repository),
                getItems = GetItems(realtimeRepository = repository)
            )
}
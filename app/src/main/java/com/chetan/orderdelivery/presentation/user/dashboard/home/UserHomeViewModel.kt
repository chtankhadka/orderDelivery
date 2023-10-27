package com.chetan.orderdelivery.presentation.user.dashboard.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chetan.orderdelivery.data.Resource
import com.chetan.orderdelivery.data.model.PushNotificationRequest
import com.chetan.orderdelivery.domain.repository.DBRepository
import com.chetan.orderdelivery.domain.repository.OneSignalRepository
import com.chetan.orderdelivery.domain.use_cases.firestore.FirestoreUseCases
import com.chetan.orderdelivery.domain.use_cases.realtime.RealtimeUseCases
import com.onesignal.OneSignal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class UserHomeViewModel @Inject constructor(
    private val firestoreUseCases: FirestoreUseCases,
    private val realtimeUseCases: RealtimeUseCases,
    private val dbRepository: DBRepository,
    private val oneSiganlRepository: OneSignalRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(UserHomeState())
    val state: StateFlow<UserHomeState> = _state

    init {
        getFavList()
        getAllFoods()
        println("----------------------------")
        OneSignal.User.pushSubscription.token
        println(OneSignal.User.pushSubscription.id)

        viewModelScope.launch {


            try {
                val sendNotification = oneSiganlRepository.pushNotification(
                    PushNotificationRequest(
                        contents = mapOf("en" to "hi"), name = "test"
                    )
                )
                when (sendNotification) {
                    is Resource.Failure -> {
//                        _state.update {
//                            it.copy(
//                                infoMsg = Message.Error(
//                                    lottieImage = R.raw.delete_simple,
//                                    yesNoRequired = false,
//                                    isCancellable = false,
//                                    description = "Error..."
//                                )
//                            )
//
//                        }
                    }

                    Resource.Loading -> {

                    }

                    is Resource.Success -> {
//                        _state.update {
//                            it.copy(
//                                infoMsg = Message.Success(
//                                    lottieImage = R.raw.pencil_walking,
//                                    isCancellable = true,
//                                    description = "Success"
//                                )
//                            )
//                        }
                    }
                }
            } catch (e: HttpException) {
                _state.update {
                    it.copy(
                        infoMsg = null
                    )
                }
                e.printStackTrace()
            } catch (e: Throwable) {
                _state.update {
                    it.copy(
                        infoMsg = null
                    )
                }
                e.printStackTrace()
            }
        }


    }

    private fun getAllFoods() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    allFoods = dbRepository.getAllFoods()
                )
            }
        }

    }

    private fun getFavList() {
        viewModelScope.launch {
            val allFavList = firestoreUseCases.getFavouriteList()
            when (allFavList) {
                is Resource.Failure -> {

                }

                Resource.Loading -> {

                }

                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            favouriteList = allFavList.data
                        )
                    }
                }
            }
        }
    }

    val onEvent: (event: UserHomeEvent) -> Unit = { event ->
        viewModelScope.launch {
            when (event) {
                UserHomeEvent.DismissInfoMsg -> {

                    _state.update {
                        it.copy(
                            infoMsg = null
                        )
                    }
                }

                UserHomeEvent.AddToCart -> {

                }

                UserHomeEvent.More -> {

                }

                is UserHomeEvent.SetFavourite -> {
                    val setFav = firestoreUseCases.setFavourite(
                        foodId = event.foodId, isFavourite = event.isFav
                    )
                    when (setFav) {
                        is Resource.Failure -> {

                        }

                        Resource.Loading -> {

                        }

                        is Resource.Success -> {
                            if (setFav.data) {
                                getFavList()
                            }
                        }
                    }
                }
            }
        }

    }


}
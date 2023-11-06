package com.chetan.orderdelivery.presentation.user.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chetan.orderdelivery.data.Resource
import com.chetan.orderdelivery.data.local.Preference
import com.chetan.orderdelivery.data.model.ProfileRequestResponse
import com.chetan.orderdelivery.domain.use_cases.firestore.FirestoreUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    private val firestoreUseCases: FirestoreUseCases,
    private val preference: Preference
) : ViewModel() {
    private val _state = MutableStateFlow(UserProfileState())
    val state: StateFlow<UserProfileState> = _state
    init {
        getProfile()
    }
    fun getProfile() {
        viewModelScope.launch {
            val profile = firestoreUseCases.getUserProfile(preference.tableName ?: "test")
            when (profile) {
                is Resource.Failure -> {
                }

                Resource.Loading -> {
                }

                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            name = profile.data.name,
                            phoneNo = profile.data.phoneNo,
                            address = profile.data.address
                        )
                    }
                }
            }
        }
    }


    val onEvent: (event: UserProfileEvent) -> Unit = { event ->
        viewModelScope.launch {
            when (event) {
                UserProfileEvent.UpdateProfile -> {
                    val update = firestoreUseCases.updateUserProfile(
                        ProfileRequestResponse(
                            name = state.value.name,
                            phoneNo = state.value.phoneNo,
                            address = state.value.address
                        )
                    )
                    when (update) {
                        is Resource.Failure -> {

                        }

                        Resource.Loading -> {

                        }

                        is Resource.Success -> {
                            if (update.data) {

                            }
                        }
                    }
                }

                is UserProfileEvent.OnAddressChange -> {
                    _state.update {
                        it.copy(
                            address = event.value
                        )
                    }
                }

                is UserProfileEvent.OnNameChange -> {
                    _state.update {
                        it.copy(
                            name = event.value
                        )
                    }
                }

                is UserProfileEvent.OnPhoneChange -> {
                    _state.update {
                        it.copy(
                            phoneNo = event.value
                        )
                    }
                }

                UserProfileEvent.DismissInfoMsg ->{
                    _state.update {
                        it.copy(
                            infoMsg = null
                        )
                    }
                }
            }
        }
    }
}
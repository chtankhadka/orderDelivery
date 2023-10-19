package com.chetan.orderdelivery.presentation.user.dashboard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.chetan.orderdelivery.data.local.Preference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class UserDashboardViewModel @Inject constructor(
    private val preference: Preference
): ViewModel() {

    private val _state = MutableStateFlow(UserDashboardState())
    val state : StateFlow<UserDashboardState> = _state



    init {
        _state.update {
            it.copy(
                darkMode = preference.isDarkMode.value
            )
        }
    }

    val onEvent : (event : UserDashboardEvent) -> Unit = {event ->
        when(event){
            is UserDashboardEvent.ChangeDarkMode -> {
                preference.isDarkMode = mutableStateOf(!state.value.darkMode)
                _state.update {
                    it.copy(
                        darkMode = !state.value.darkMode
                    )
                }
            }
        }
    }
}
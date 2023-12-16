package com.gym.gymmate.feature.splash

import androidx.lifecycle.viewModelScope
import com.gym.gymmate.core.common.BaseViewModel
import com.gym.gymmate.core.common.IViewEvents
import com.gym.gymmate.core.common.IViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : BaseViewModel<SplashViewState, SplashViewEvents>() {
    override fun createInitialState(): SplashViewState = SplashViewState()

    init {
        viewModelScope.launch {
            delay(1000)
            fireEvent(SplashViewEvents.NavigateToLogin)
        }
    }
}

data class SplashViewState(val loading: Boolean = false) : IViewState

sealed class SplashViewEvents : IViewEvents {
    data object NavigateToHome : SplashViewEvents()
    data object NavigateToLogin : SplashViewEvents()
}
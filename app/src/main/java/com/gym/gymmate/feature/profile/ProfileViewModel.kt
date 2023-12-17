package com.gym.gymmate.feature.profile

import com.gym.gymmate.core.common.BaseViewModel
import com.gym.gymmate.core.common.IViewEvents
import com.gym.gymmate.core.common.IViewState
import javax.inject.Inject

class ProfileViewModel @Inject constructor() :
    BaseViewModel<ProfileViewState, ProfileViewEvents>() {
    override fun createInitialState(): ProfileViewState = ProfileViewState()
}

data class ProfileViewState(val loading: Boolean = false) : IViewState

sealed class ProfileViewEvents : IViewEvents {}
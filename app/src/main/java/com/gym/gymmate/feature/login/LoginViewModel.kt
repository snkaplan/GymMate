package com.gym.gymmate.feature.login

import androidx.lifecycle.viewModelScope
import com.gym.gymmate.core.common.BaseViewModel
import com.gym.gymmate.core.common.IViewEvents
import com.gym.gymmate.core.common.IViewState
import com.gym.gymmate.core.common.Resource
import com.gym.gymmate.core.common.asResource
import com.gym.gymmate.core.domain.login.LoginUseCase
import com.gym.gymmate.core.domain.login.ValidateAuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val validateAuthUseCase: ValidateAuthUseCase,
) : BaseViewModel<LoginViewState, LoginViewEvents>() {

    override fun createInitialState(): LoginViewState = LoginViewState()

    fun onLoginClick() {
        viewModelScope.launch {
            validateAuthUseCase.invoke(currentState.userName, currentState.password)
                .onEach { result ->
                    when (result) {
                        Resource.Loading -> {
                            updateState { copy(loading = true) }
                        }

                        is Resource.Error -> {
                            updateState { copy(loading = false) }
                        }

                        is Resource.Success -> {
                            updateState { copy(loading = false) }
                            onLogin(currentState.userName, currentState.password)
                        }
                    }
                }.launchIn(this)
        }
    }

    private fun onLogin(username: String, password: String) {
        viewModelScope.launch {
            loginUseCase(username, password)
                .asResource()
                .onEach { result ->
                    when (result) {
                        Resource.Loading -> {
                            updateState { copy(loading = true) }
                        }

                        is Resource.Error -> {
                            updateState { copy(loading = false) }
                        }

                        is Resource.Success -> {
                            updateState {
                                copy(loading = false)
                            }
                            fireEvent(LoginViewEvents.NavigateToHome)
                        }
                    }
                }.launchIn(this)
        }
    }

}

data class LoginViewState(
    val loading: Boolean = false,
    val userName: String = "",
    val password: String = "",
    val passwordErrorMessage: Int? = null,
    val usernameErrorMessage: Int? = null
) : IViewState


sealed class LoginViewEvents : IViewEvents {
    data object NavigateToHome : LoginViewEvents()
}
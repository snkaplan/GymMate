package com.gym.gymmate.feature.splash

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun SplashScreen(
    navigateToLogin: () -> Unit,
    navigateToHome: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SplashViewModel = hiltViewModel()
) {
    val splashUiState by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(key1 = viewModel.uiEvents) {
        viewModel.uiEvents.collect { event ->
            when (event) {
                SplashViewEvents.NavigateToHome -> navigateToHome.invoke()
                SplashViewEvents.NavigateToLogin -> navigateToLogin.invoke()
            }
        }
    }
    ScreenContent(viewState = splashUiState, modifier = modifier)
}

@Composable
private fun ScreenContent(viewState: SplashViewState, modifier: Modifier) {
    Text(text = "Splash Screen")
}
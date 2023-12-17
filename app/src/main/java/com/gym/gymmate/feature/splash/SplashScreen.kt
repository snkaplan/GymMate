package com.gym.gymmate.feature.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gym.gymmate.R
import com.gym.gymmate.core.design.components.TitleMedium

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
    ScreenContent(viewState = splashUiState, modifier = modifier.fillMaxSize())
}

@Composable
private fun ScreenContent(viewState: SplashViewState, modifier: Modifier) {
    Column(
        modifier = modifier.background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.mipmap.ic_gym_mate_foreground),
            contentDescription = "App Icon"
        )
        Spacer(modifier = Modifier.height(15.dp))
        TitleMedium(text = stringResource(id = R.string.lets_workout))
    }
}
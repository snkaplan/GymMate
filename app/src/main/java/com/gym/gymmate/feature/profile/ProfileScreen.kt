package com.gym.gymmate.feature.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gym.gymmate.core.design.components.TitleMedium

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val splashUiState by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(key1 = viewModel.uiEvents) {
        viewModel.uiEvents.collect { event ->
        }
    }
    ScreenContent(viewState = splashUiState, modifier = modifier)
}

@Composable
private fun ScreenContent(viewState: ProfileViewState, modifier: Modifier) {
    TitleMedium(text = "Profile Screen")
}
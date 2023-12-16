package com.gym.gymmate.feature.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.gym.gymmate.core.design.components.GymMateScaffold
import com.gym.gymmate.core.design.ui.TrackScrollJank
import com.gym.gymmate.core.domain.category.CategoryItem

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val viewState by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(key1 = viewModel.uiEvents) {
        viewModel.uiEvents.collect { event ->
            when (event) {
                is HomeViewEvents.NavigateCategory -> {

                }
            }
        }
    }
    ScreenContent(
        viewState = viewState,
        modifier = modifier,
        onCategoryClick = viewModel::onCategoryClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ScreenContent(
    viewState: HomeViewState,
    modifier: Modifier,
    onCategoryClick: (categoryItem: CategoryItem) -> Unit
) {
    GymMateScaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = {
                    Text(text = "Hello")
                },
            )
        },
    ) { padding ->
        val scrollableState = rememberLazyListState()
        TrackScrollJank(scrollableState = scrollableState, stateName = "home:LazyList")

        Column(
            modifier = modifier
                .padding(padding)
                .fillMaxSize()
                .imePadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Card(
                modifier = Modifier.fillMaxSize(),
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Blue.copy(alpha = 0.1f))
            ) {
                LazyColumn(
                    state = scrollableState,
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(vertical = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    item {
                        viewState.categoryResult.categoryItems.takeIf { it.isNotEmpty() }?.let {
                            HomeCategoryList(categoryList = it, onCategoryClick)
                        }
                    }
                    item {
                        if (viewState.loading) {
                            CircularProgressIndicator(modifier = Modifier)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HomeCategoryList(
    categoryList: List<CategoryItem>,
    onCategoryClick: (categoryItem: CategoryItem) -> Unit
) {
    val categoryScrollState = rememberLazyListState()
    TrackScrollJank(scrollableState = categoryScrollState, stateName = "home:CategoryList")

    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        HomeHeaderItem(text = "Exercise Categories", modifier = Modifier.padding(start = 20.dp))
        LazyRow(
            state = categoryScrollState,
            contentPadding = PaddingValues(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(categoryList) { _, item ->
                HomeCategoryItem(
                    categoryItem = item,
                    onClick = {
                        onCategoryClick(item)
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun HomeCategoryItem(
    categoryItem: CategoryItem,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(modifier = Modifier.width(150.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .clickable(onClick = onClick)
        ) {
            GlideImage(
                model = categoryItem.imageResource,
                contentDescription = "Category Image",
                modifier = modifier.fillMaxSize()
            )
        }
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = categoryItem.title,
            style = MaterialTheme.typography.labelLarge,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun HomeHeaderItem(text: String, modifier: Modifier = Modifier) {
    Text(text = text, style = MaterialTheme.typography.titleMedium, modifier = modifier)
}


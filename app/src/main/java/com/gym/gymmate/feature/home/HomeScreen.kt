package com.gym.gymmate.feature.home

import androidx.compose.foundation.background
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.gym.gymmate.R
import com.gym.gymmate.core.data.util.getCurrentDateWithFormat
import com.gym.gymmate.core.design.components.BoldLabelMedium
import com.gym.gymmate.core.design.components.GymMateAppTopBar
import com.gym.gymmate.core.design.components.GymMateScaffold
import com.gym.gymmate.core.design.components.TitleMedium
import com.gym.gymmate.core.design.icon.AppIcons
import com.gym.gymmate.core.design.ui.TrackScrollJank
import com.gym.gymmate.core.design.ui.theme.Light_Grey
import com.gym.gymmate.core.domain.category.CategoryItem

@Composable
fun HomeScreen(
    navigateToProfile: () -> Unit,
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
        navigateToProfile = navigateToProfile,
        viewState = viewState,
        modifier = modifier,
        onCategoryClick = viewModel::onCategoryClick
    )
}

@Composable
private fun ScreenContent(
    navigateToProfile: () -> Unit,
    viewState: HomeViewState,
    modifier: Modifier,
    onCategoryClick: (categoryItem: CategoryItem) -> Unit
) {
    GymMateScaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            GymMateAppTopBar(
                backgroundColor = Light_Grey,
                title = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontSize = 20.sp)) {
                        appendLine(stringResource(id = R.string.hello_user, ""))
                    }
                    withStyle(style = SpanStyle(fontSize = 10.sp, fontWeight = FontWeight.Normal)) {
                        append(
                            stringResource(
                                id = R.string.today_is,
                                getCurrentDateWithFormat("dd MMMM")
                            )
                        )
                    }
                },
                rightIcon = AppIcons.ProfileIcon,
                onRightIconClicked = { navigateToProfile.invoke() }
            )
        },
    ) { padding ->
        val scrollableState = rememberLazyListState()
        TrackScrollJank(scrollableState = scrollableState, stateName = "home:LazyList")

        Column(
            modifier = modifier
                .padding(padding)
                .background(Light_Grey)
                .fillMaxSize()
                .imePadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Card(
                modifier = Modifier.fillMaxSize(),
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
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
        HomeHeaderItem(
            text = stringResource(id = R.string.categories),
            modifier = Modifier.padding(start = 20.dp)
        )
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
    Card(
        modifier = modifier.fillMaxSize(),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.elevatedCardElevation(5.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .width(150.dp)
                .background(Light_Grey),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            GlideImage(
                model = categoryItem.imageResource,
                contentDescription = "Category Image",
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(onClick = onClick)

            )
            BoldLabelMedium(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 5.dp, end = 5.dp, bottom = 5.dp),
                text = categoryItem.title,
                textAlign = TextAlign.Center
            )
        }
    }

}

@Composable
fun HomeHeaderItem(text: String, modifier: Modifier = Modifier) {
    TitleMedium(text = text, modifier = modifier)
}


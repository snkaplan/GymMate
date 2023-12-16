package com.gym.gymmate.app.navigation

import com.gym.gymmate.R
import com.gym.gymmate.core.design.icon.AppIcons
import com.gym.gymmate.core.design.icon.Icon
import com.gym.gymmate.feature.home.HomeNavigationRoute

enum class TopLevelDestinations(
    val route: String,
    val selectedIcon: Icon,
    val unselectedIcon: Icon,
    val titleTextId: Int,
) {
    HOME(
        route = HomeNavigationRoute,
        selectedIcon = Icon.ImageVectorIcon(AppIcons.Home),
        unselectedIcon = Icon.ImageVectorIcon(AppIcons.HomeOutlined),
        titleTextId = R.string.home_title,
    )
}
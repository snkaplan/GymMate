package com.gym.gymmate.core.design.icon

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.vector.ImageVector

object AppIcons {
    val Home = Icons.Default.Home
    val HomeOutlined = Icons.Outlined.Home
    val BackIcon = Icons.Default.KeyboardArrowLeft
    val ProfileIcon = Icons.Default.Person
    val ProfileIconOutlined = Icons.Outlined.Person
}

@Stable
sealed class Icon {
    data class ImageVectorIcon(val imageVector: ImageVector) : Icon()
    data class DrawableResourceIcon(@DrawableRes val id: Int) : Icon()
}

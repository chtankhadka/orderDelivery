package com.chetan.orderdelivery.presentation.common.utils.bottomnavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
){
    data object Home: BottomBarScreen(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home
    )
    data object Profile: BottomBarScreen(
        route = "profile",
        title = "Home",
        icon = Icons.Default.Person
    )
    data object Map: BottomBarScreen(
        route = "map",
        title = "Map",
        icon = Icons.Default.LocationOn
    )
}

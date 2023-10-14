package com.chetan.orderdelivery.presentation.admin.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocalDrink
import androidx.compose.material.icons.filled.LocalPizza
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun AdminDashboardModalDrawerPage(
    onClick: (MenuItem) -> Unit,
){

    val menuList = listOf(
        MenuItem.AddFoodItem,
        MenuItem.UpdateRating,
        MenuItem.AddDrinks,
        MenuItem.User
    )
    Column(
        modifier = Modifier
            .fillMaxWidth(0.7f)
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        menuList.forEach {menuItem ->
            ElevatedCard(
                modifier = Modifier.clickable {
                    onClick(menuItem)
                },
                shape = RoundedCornerShape(5.dp)
            ) {
                Text(text = menuItem.label)

            }

        }


    }
}


sealed class MenuItem(val icon: ImageVector, val label: String){
    data object AddFoodItem: MenuItem(icon = Icons.Default.LocalPizza, label = "Add Food Item")
    data object UpdateRating: MenuItem(icon = Icons.Default.StarRate, label = "Update Rating")
    data object AddDrinks: MenuItem(icon = Icons.Default.LocalDrink, label = "Add Drinks")
    data object User: MenuItem(icon = Icons.Default.Person, label = "User")
}
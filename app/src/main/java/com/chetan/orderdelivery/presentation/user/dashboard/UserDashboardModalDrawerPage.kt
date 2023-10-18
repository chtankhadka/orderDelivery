package com.chetan.orderdelivery.presentation.user.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Contacts
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.LocalDrink
import androidx.compose.material.icons.filled.LocalPizza
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun UserDashboardModalDrawerPage(
    onClick: (MenuItem) -> Unit,
) {

    val topMenuList = listOf(
        MenuItem.Admin
    )
    val bottomMenuList = listOf(
        MenuItem.Contacts,
        MenuItem.Setting,
        MenuItem.Logout
    )
    Column(
        modifier = Modifier
            .fillMaxWidth(0.7f)
            .fillMaxHeight()
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(topEnd = 20.dp, bottomEnd = 20.dp)
            )
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            shape = RoundedCornerShape(
                topStart = 0.dp,
                topEnd = 20.dp,
                bottomEnd = 20.dp,
                bottomStart = 20.dp
            ),
            elevation = CardDefaults.cardElevation(10.dp)
        ) {

        }
        Spacer(modifier = Modifier.height(10.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            topMenuList.forEach { menuItem ->
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onClick(menuItem)
                        },
                    shape = RoundedCornerShape(5.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            modifier = Modifier.padding(5.dp),
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = menuItem.icon,
                                contentDescription = menuItem.label,
                                tint = MaterialTheme.colorScheme.primary,
                            )
                            Text(
                                text = menuItem.label,
                                style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.primary)
                            )
                        }
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = "arrow right",
                            tint = MaterialTheme.colorScheme.primary,
                        )
                    }


                }
                Spacer(modifier = Modifier.height(5.dp))

            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Divider()

        bottomMenuList.forEach {item ->
            ElevatedCard(
                modifier = Modifier
                    .clickable {
                        onClick(item)
                    },
                shape = RoundedCornerShape(5.dp),
                elevation = CardDefaults.cardElevation(0.dp)
            ) {
                    Row(
                        modifier = Modifier.padding(5.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.label,
                            tint = MaterialTheme.colorScheme.primary,
                        )
                        Text(
                            text = item.label,
                            style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.primary)
                        )
                    }
            }
            Spacer(modifier = Modifier.height(5.dp))
        }

    }
}

sealed class MenuItem(val icon: ImageVector, val label: String) {
    data object Admin : MenuItem(icon = Icons.Default.Person, label = "Admin")
    data object Contacts: MenuItem(icon = Icons.Default.Contacts, label = "Contacts")
    data object Setting : MenuItem(icon = Icons.Default.Settings, label = "Setting")
    data object Logout : MenuItem(icon = Icons.Default.Logout, label = "LogOut")
}
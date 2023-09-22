package com.chetan.orderdelivery.presentation.user.dashboard

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.chetan.orderdelivery.R
import com.chetan.orderdelivery.presentation.common.utils.BottomNavigate.bottomNavigate
import com.chetan.orderdelivery.presentation.user.dashboard.home.UserHomeScreen
import com.chetan.orderdelivery.presentation.user.dashboard.home.UserHomeViewModel
import kotlinx.coroutines.delay


data class UserInnerPage(
    val route: String, val label: Int, val icon: ImageVector
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDashboardScreen(onBack: () -> Unit, navController: NavHostController) {
    var backPressCount by remember {
        mutableIntStateOf(0)
    }
    val context = LocalContext.current
    LaunchedEffect(key1 = backPressCount, block = {
        if (backPressCount == 1) {
            delay(2000)
            backPressCount = 0
        }
    })

    BackHandler {
        if (backPressCount == 0) {
            Toast.makeText(context, "Press again to exit", Toast.LENGTH_SHORT).show()
            backPressCount++
        } else if (backPressCount == 1) {
            onBack()
        }
    }
    val items: List<UserInnerPage> = remember {
        listOf(
            UserInnerPage("home", R.string.home, Icons.Default.Home),
            UserInnerPage("cart", R.string.cart, Icons.Default.ShoppingCart),
            UserInnerPage("history", R.string.history, Icons.Default.History)
        )
    }
    val bottomNavController = rememberNavController()
    Scaffold(modifier = Modifier, topBar = {
        TopAppBar(title = { Text(text = "Test") })
    }, bottomBar = {
        BottomAppBar {
            val navBackStackEntry by bottomNavController.currentBackStackEntryAsState()
            items.forEach { screen ->
                val isSelected =
                    navBackStackEntry?.destination?.hierarchy?.any { it.route == screen.route } == true
                val color =
                    if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline

                CompositionLocalProvider(LocalContentColor provides color) {
                    NavigationBarItem(
                        colors = NavigationBarItemDefaults.colors(color),
                        icon = {
                            Icon(
                                imageVector = screen.icon,
                                contentDescription = null
                            )
                        },
                        selected = isSelected,
                        onClick = { bottomNavController.bottomNavigate(screen.route) },
                        label = {
                            Text(
                                stringResource(screen.label),
                                style = MaterialTheme.typography.labelSmall
                            )
                        })

                }

            }
        }
    }, content = {
        NavHost(
            modifier = Modifier.padding(it),
            navController = bottomNavController,
            startDestination = "home"
        ) {
            composable("home") {
                val viewModel = hiltViewModel<UserHomeViewModel>()
                UserHomeScreen(
                    navController = navController,
                    state = viewModel.state.collectAsStateWithLifecycle().value,
                    event = viewModel.onEvent

                )

            }
            composable("cart") {

            }
            composable("history") {

            }

        }
    })
}

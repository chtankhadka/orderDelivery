package com.chetan.orderdelivery.presentation.admin.dashboard

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import com.chetan.orderdelivery.presentation.admin.dashboard.home.HomeScreen
import com.chetan.orderdelivery.presentation.admin.dashboard.map.AdminMapViewModel
import com.chetan.orderdelivery.presentation.admin.dashboard.map.MapScreen
import com.chetan.orderdelivery.presentation.common.utils.BottomNavigate.bottomNavigate
import kotlinx.coroutines.delay


data class AdminInnerPage(
    val route: String, val label: Int, val icon: ImageVector
)

@SuppressLint("MissingPermission")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminDashboardScreen(onBack: () -> Unit, navController: NavHostController) {
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
            onBack() // Call your onBack function
        }
    }
    val items: List<AdminInnerPage> = remember {
        listOf(
            AdminInnerPage("home", R.string.home, Icons.Default.Home),
            AdminInnerPage("map", R.string.map, Icons.Default.LocationOn)
        )
    }

    val bottomNavController = rememberNavController()
    Scaffold(modifier = Modifier, topBar = {}, bottomBar = {
        BottomAppBar {
            val navBackStackEntry by bottomNavController.currentBackStackEntryAsState()
            items.forEach { screen ->
                val isSelected =
                    navBackStackEntry?.destination?.hierarchy?.any { it.route == screen.route } == true
                val color =
                    if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline
                CompositionLocalProvider(LocalContentColor provides color) {
                    NavigationBarItem(icon = { Icon(screen.icon, contentDescription = null) },
                        label = {
                            Text(
                                stringResource(screen.label),
                                style = MaterialTheme.typography.labelSmall
                            )
                        },
                        selected = isSelected,
                        onClick = { bottomNavController.bottomNavigate(screen.route) })
                }
            }

        }
    }, content = {
        NavHost(
            navController = bottomNavController,
            startDestination = "home",
            modifier = Modifier.padding(it)
        ) {
            composable("home") {
                HomeScreen(
                )
            }
            composable("map") {
                val viewModel = hiltViewModel<AdminMapViewModel>()
                MapScreen(
                    state = viewModel.state.collectAsStateWithLifecycle().value,
                    onEvent = viewModel.onEvent
                )
            }
        }
    })

}
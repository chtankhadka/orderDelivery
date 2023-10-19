package com.chetan.orderdelivery.presentation.admin.dashboard

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.chetan.orderdelivery.Destination
import com.chetan.orderdelivery.R
import com.chetan.orderdelivery.common.ApplicationAction
import com.chetan.orderdelivery.common.Constants
import com.chetan.orderdelivery.presentation.admin.dashboard.home.HomeScreen
import com.chetan.orderdelivery.presentation.admin.dashboard.map.AdminMapViewModel
import com.chetan.orderdelivery.presentation.admin.dashboard.map.MapScreen
import com.chetan.orderdelivery.presentation.common.components.LoadLottieAnimation
import com.chetan.orderdelivery.presentation.common.utils.BottomNavigate.bottomNavigate
import com.chetan.orderdelivery.presentation.common.utils.CleanNavigate.cleanNavigate
import com.chetan.orderdelivery.presentation.user.dashboard.UserDashboardEvent
import kotlinx.coroutines.delay


data class AdminInnerPage(
    val route: String, val label: Int, val icon: ImageVector
)

@SuppressLint("MissingPermission")
@Composable
fun AdminDashboardScreen(
    onBack: () -> Unit,
    navController: NavHostController,
    state: AdminDashboardState,
    onEvent: (event: AdminDashboardEvent) -> Unit,
    onAction: (ApplicationAction) -> Unit
) {
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

    var showApplyThemeDialog by remember {
        mutableStateOf(false)
    }

    if (showApplyThemeDialog){
        Dialog(onDismissRequest = {
        }) {
            Column(
                Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.background)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Action Needed",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge
                )
//            AsyncImage(
//                model = message.image,
//                modifier = Modifier.size(145.dp),
//                contentDescription = null
//            )

                LoadLottieAnimation(
                    modifier = Modifier.size(200.dp) ,
                    image = R.raw.loading_food)

                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Please restart the app to apply the theme.",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.outline)
                )
                Spacer(modifier = Modifier.height(34.dp))

                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(5.dp).also { Arrangement.Center }){
                    Button(
                        modifier = Modifier.weight(1f),
                        onClick = {
                            onAction(ApplicationAction.Restart)
                        },
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.error)
                    ) {
                        Text(text = "Restart")
                    }
                    Button(
                        modifier = Modifier.weight(1f),
                        onClick = {
                            showApplyThemeDialog = false
                        },
                        colors = ButtonDefaults.buttonColors(Constants.dark_primaryContainer)
                    ) {
                        Text(text = "Cancel")
                    }
                }

            }
        }
    }

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
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        scrimColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.4f),
        drawerContent = {
            AdminDashboardModalDrawerPage(
                state = state,
                onClick = {
                    when(it){
                        MenuItem.AddDrinks -> {

                        }
                        MenuItem.AddFoodItem -> {
                            navController.navigate(Destination.Screen.AdminAddFoodScreen.route)
                        }
                        MenuItem.UpdateRating -> {
                            navController.navigate(Destination.Screen.AdminRatingUpdateScreen.route)
                        }

                        MenuItem.User -> {
                            navController.cleanNavigate(Destination.Screen.UserDashboardScreen.route)
                        }

                        MenuItem.Logout -> {
                            onAction(ApplicationAction.Logout)
                        }
                        MenuItem.Setting -> {

                        }

                        MenuItem.DarkMode ->{
                            showApplyThemeDialog = true
                            onEvent(AdminDashboardEvent.ChangeDarkMode)
                        }
                    }
                })
        }) {
        Scaffold(
            modifier = Modifier,
            topBar = {},
            bottomBar = {
                BottomAppBar {
                    val navBackStackEntry by bottomNavController.currentBackStackEntryAsState()
                    items.forEach { screen ->
                        val isSelected =
                            navBackStackEntry?.destination?.hierarchy?.any { it.route == screen.route } == true
                        val color =
                            if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline
                        CompositionLocalProvider(LocalContentColor provides color) {
                            NavigationBarItem(icon = {
                                Icon(
                                    screen.icon,
                                    contentDescription = null
                                )
                            },
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
            },
            content = {
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

}
package com.chetan.orderdelivery.presentation.common.utils.bottomnavigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.chetan.orderdelivery.presentation.admin.dashboard.home.HomeScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(BottomBarScreen.Home.route){
            HomeScreen()
        }
        composable(BottomBarScreen.Profile.route){
            HomeScreen()
        }
        composable(BottomBarScreen.Map.route){
            HomeScreen()
        }
    }
}
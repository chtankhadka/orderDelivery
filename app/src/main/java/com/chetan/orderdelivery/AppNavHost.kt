package com.chetan.orderdelivery

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.chetan.orderdelivery.common.ApplicationAction
import com.chetan.orderdelivery.presentation.admin.dashboard.AdminDashboardScreen
import com.chetan.orderdelivery.presentation.admin.dashboard.AdminDashboardViewModel
import com.chetan.orderdelivery.presentation.admin.food.addfood.AddFoodScreen
import com.chetan.orderdelivery.presentation.admin.food.addfood.AddFoodViewModel
import com.chetan.orderdelivery.presentation.admin.food.ratingUpdate.RatingUpdateScreen
import com.chetan.orderdelivery.presentation.admin.food.ratingUpdate.RatingUpdateViewModel
import com.chetan.orderdelivery.presentation.common.google_sign_in.GoogleAuthUiClient
import com.chetan.orderdelivery.presentation.common.google_sign_in.SignInScreen
import com.chetan.orderdelivery.presentation.common.google_sign_in.SignInViewModel
import com.chetan.orderdelivery.presentation.common.utils.CleanNavigate.cleanNavigate
import com.chetan.orderdelivery.presentation.user.dashboard.UserDashboardScreen
import com.chetan.orderdelivery.presentation.user.dashboard.UserDashboardViewModel
import com.chetan.orderdelivery.presentation.user.foodorderdescription.FoodOrderDescriptionScreen
import com.chetan.orderdelivery.presentation.user.foodorderdescription.FoodOrderDescriptionViewModel
import com.chetan.orderdelivery.presentation.user.morefood.MoreFoodScreen
import com.chetan.orderdelivery.presentation.user.notification.NotificationScreen
import com.chetan.orderdelivery.presentation.user.ordercheckout.OrderCheckoutScreen
import com.chetan.orderdelivery.presentation.user.ordercheckout.OrderCheckoutViewModel
import com.chetan.orderdelivery.presentation.user.outCart.OutUserCartScreen
import com.chetan.orderdelivery.presentation.user.outCart.OutUserCartViewModel
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavHost(
    navController: NavHostController,
    onBack: () -> Unit,
    googleAuthUiClient: GoogleAuthUiClient,
    lifecycleScope: LifecycleCoroutineScope,
    applicationContext: Context
) {
    NavHost(
        navController = navController,
        startDestination = Destination.Screen.CommonSignInScreen.route
    ) {
        // common
        composable(Destination.Screen.CommonSignInScreen.route) {
            val viewModel = hiltViewModel<SignInViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()

            LaunchedEffect(key1 = Unit, block = {
                if (googleAuthUiClient.getSignedInUser() != null) {
                    println(googleAuthUiClient.getSignedInUser()!!.userEmail)
                    if (googleAuthUiClient.getSignedInUser()!!.userEmail == "chtankhadka12@gmail.com") {
                        navController.cleanNavigate(Destination.Screen.AdminDashboardScreen.route)
                    } else {
                        navController.cleanNavigate(Destination.Screen.UserDashboardScreen.route)
                    }
                }
            })
            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartIntentSenderForResult(),
                onResult = { result ->
                    if (result.resultCode == ComponentActivity.RESULT_OK) {
                        lifecycleScope.launch {
                            val signInResult = googleAuthUiClient.signInWithIntent(
                                intent = result.data ?: return@launch
                            )
                            viewModel.onSignInResult(signInResult)
                        }
                    }

                }
            )

            LaunchedEffect(key1 = state.isSignInSuccessful, block = {
                if (state.isSignInSuccessful) {
                    viewModel.resetState()
                    navController.cleanNavigate(Destination.Screen.AdminDashboardScreen.route)

                }
            })
            SignInScreen(state = state) {
                lifecycleScope.launch {
                    val signInIntentSender = googleAuthUiClient.signIn()
                    launcher.launch(
                        IntentSenderRequest.Builder(
                            signInIntentSender ?: return@launch
                        ).build()
                    )
                }
            }
        }


        // User
        composable(Destination.Screen.UserDashboardScreen.route) {
            val viewModel = hiltViewModel<UserDashboardViewModel>()
            UserDashboardScreen(
                onBack = onBack,
                navController = navController,
                state = viewModel.state.collectAsStateWithLifecycle().value,
                onEvent = viewModel.onEvent,
                onAction = { applicationAction ->
                    when (applicationAction) {
                        ApplicationAction.Restart -> {
                            val intent = Intent(applicationContext, MainActivity::class.java)
                            intent.flags =
                                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            applicationContext.startActivity(intent)
                        }
                        ApplicationAction.Logout -> {
                            lifecycleScope.launch {
                                googleAuthUiClient.signOut()
                                navController.cleanNavigate(Destination.Screen.CommonSignInScreen.route)
                            }
                        }
                    }
                }
            )
        }

        composable(Destination.Screen.UserFoodOrderDescriptionScreen.route) {it->
            val foodId = it.arguments?.getString("foodId")!!
            val viewModel = hiltViewModel<FoodOrderDescriptionViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()
            FoodOrderDescriptionScreen(
                foodId = foodId,
                navController = navController,
                onEvent = viewModel.onEvent,
                state = state
            )
        }

        composable(Destination.Screen.UserOrderCheckoutScreen.route) {
            val viewModel = hiltViewModel<OrderCheckoutViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()
            OrderCheckoutScreen(
                navController = navController,
                onEvent = viewModel.onEvent,
                state = state
            )
        }

        composable(Destination.Screen.UserNotificationScreen.route){
            NotificationScreen()
        }
        composable(Destination.Screen.UserMoreFoodScreen.route){
            MoreFoodScreen()
        }
        composable(Destination.Screen.UserOutCartScreen.route){
            val viewModel = hiltViewModel<OutUserCartViewModel>()
            val state = viewModel.state.collectAsStateWithLifecycle().value
            OutUserCartScreen(
                navController = navController,
                state = state,
                event = viewModel.onEvent
            )
        }


        //Admin
        composable(Destination.Screen.AdminDashboardScreen.route) {
            val viewModel = hiltViewModel<AdminDashboardViewModel>()
            AdminDashboardScreen(
                navController = navController,
                onBack = onBack,
                state = viewModel.state.collectAsStateWithLifecycle().value,
                onEvent = viewModel.onEvent,
                onAction = { applicationAction ->
                    when (applicationAction) {
                        ApplicationAction.Restart -> {
                            val intent = Intent(applicationContext, MainActivity::class.java)
                            intent.flags =
                                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            applicationContext.startActivity(intent)
                        }
                        ApplicationAction.Logout -> {
                            lifecycleScope.launch {
                                googleAuthUiClient.signOut()
                                navController.cleanNavigate(Destination.Screen.CommonSignInScreen.route)
                            }
                        }
                    }
                }
            )
        }
        composable(Destination.Screen.AdminAddFoodScreen.route) {
            val viewModel = hiltViewModel<AddFoodViewModel>()
            AddFoodScreen(
                navController = navController,
                state = viewModel.state.collectAsStateWithLifecycle().value,
                onEvent = viewModel.onEvent
            )
        }
        composable(Destination.Screen.AdminRatingUpdateScreen.route) {
            val viewModel = hiltViewModel<RatingUpdateViewModel>()
            RatingUpdateScreen(
                navController = navController,
                state = viewModel.state.collectAsStateWithLifecycle().value,
                onEvent = viewModel.onEvent
            )
        }
    }
}
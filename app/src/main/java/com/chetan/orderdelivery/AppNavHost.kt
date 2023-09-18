package com.chetan.orderdelivery

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.chetan.orderdelivery.presentation.admin.dashboard.AdminDashboardScreen
import com.chetan.orderdelivery.presentation.common.google_sign_in.GoogleAuthUiClient
import com.chetan.orderdelivery.presentation.common.google_sign_in.SignInScreen
import com.chetan.orderdelivery.presentation.common.google_sign_in.SignInViewModel
import com.chetan.orderdelivery.presentation.common.utils.CleanNavigate.cleanNavigate
import com.chetan.orderdelivery.presentation.user.dashboard.UserDashboardScreen
import kotlinx.coroutines.launch

@Composable
fun AppNavHost (
    navController: NavHostController,
    onBack: () -> Unit,
    googleAuthUiClient: GoogleAuthUiClient,
    lifecycleScope: LifecycleCoroutineScope,
    applicationContext: Context
){
    NavHost(
        navController = navController,
        startDestination = Destination.Screen.CommonSignInScreen.route
    ) {
        composable(Destination.Screen.CommonSignInScreen.route) {
            val viewModel = viewModel<SignInViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()

            LaunchedEffect(key1 = Unit, block = {
                if (googleAuthUiClient.getSignedInUser() != null){
                    navController.cleanNavigate(Destination.Screen.UserDashboardScreen.route)
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
                    navController.cleanNavigate(Destination.Screen.UserDashboardScreen.route)

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

        composable(Destination.Screen.UserDashboardScreen.route){
            UserDashboardScreen {
                onBack()
            }
        }
        composable(Destination.Screen.AdminDashboardScreen.route){
            AdminDashboardScreen(onBack = onBack)
        }
    }
}
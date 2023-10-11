package com.chetan.orderdelivery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.chetan.orderdelivery.presentation.common.google_sign_in.GoogleAuthUiClient
import com.chetan.orderdelivery.presentation.user.dashboard.home.UserHomeScreen
import com.chetan.orderdelivery.presentation.user.dashboard.home.UserHomeViewModel
import com.chetan.orderdelivery.ui.theme.OrderDeliveryTheme
import com.google.android.gms.auth.api.identity.Identity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OrderDeliveryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
//                    val viewModel = hiltViewModel<UserHomeViewModel>()
//                    UserHomeScreen(
//                        navController = navController,
//                        state = viewModel.state.collectAsStateWithLifecycle().value,
//                        event = viewModel.onEvent
//
//                    )
                    AppNavHost(
                        navController = navController,
                        onBack = {
                            println("I am called")
                            finish()
                        },
                        googleAuthUiClient,
                        lifecycleScope,
                        applicationContext
                    )
                }
            }
        }
    }

}

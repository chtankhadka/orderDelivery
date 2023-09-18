package com.chetan.orderdelivery.presentation.admin.dashboard

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.delay

sealed class BackPress{
    object Idle: BackPress()
    object InitialTouch: BackPress()
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminDashboardScreen(onBack: () -> Unit) {

    var showToast by remember {
        mutableStateOf(false)
    }
    var backPressState by remember {
        mutableStateOf<BackPress>(BackPress.Idle)
    }
    val context = LocalContext.current
    if (showToast){
        Toast.makeText(context,"Press again to exit", Toast.LENGTH_SHORT).show()
        showToast = false
    }
    LaunchedEffect(key1 = backPressState, block ={
        if (backPressState == BackPress.InitialTouch){
            delay(2000)
            backPressState = BackPress.Idle
        }
    } )
    BackHandler(backPressState == BackPress.Idle) {
        backPressState = BackPress.InitialTouch
        showToast = true
    }

    val cameraPosition = rememberCameraPositionState()
    val focusedLocationProviderClient = remember {
        LocationServices.getFusedLocationProviderClient(context)
    }
    var locationInfo by remember {
        mutableStateOf("")
    }
    if (ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        // TODO: Consider calling
        //    ActivityCompat#requestPermissions
        // here to request the missing permissions, and then overriding
        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
        //                                          int[] grantResults)
        // to handle the case where the user grants the permission. See the documentation
        // for ActivityCompat#requestPermissions for more details.
        return
    }
    focusedLocationProviderClient.lastLocation.addOnSuccessListener { location: Location? ->


    }


    Scaffold(
        modifier = Modifier,
        topBar = {},
        bottomBar = {},
        content = {
            Column(modifier = Modifier
                .padding(it)
                .fillMaxSize()) {
//                GoogleMap(
//                    modifier = Modifier,
//                    cameraPositionState =
//                ) {
//
//                }
                CurrentLocationScreen()
            }
        }
    )
}
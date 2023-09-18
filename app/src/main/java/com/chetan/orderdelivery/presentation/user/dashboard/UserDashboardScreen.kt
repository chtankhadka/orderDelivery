package com.chetan.orderdelivery.presentation.user.dashboard

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.provider.Settings
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.chetan.orderdelivery.R
import com.chetan.orderdelivery.presentation.admin.dashboard.RequestPermission
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberMarkerState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

sealed class BackPress {
    object Idle : BackPress()
    object InitialTouch : BackPress()
}

@SuppressLint("MissingPermission")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun UserDashboardScreen(onBack: () -> Unit) {
    var showToast by remember { mutableStateOf(false) }
    var backPressState by remember {
        mutableStateOf<BackPress>(BackPress.Idle)
    }
    val context = LocalContext.current

    if (showToast) {
        Toast.makeText(context, "Press again to exit", Toast.LENGTH_SHORT).show()
        showToast = false
    }


    LaunchedEffect(key1 = backPressState) {
        if (backPressState == BackPress.InitialTouch) {
            delay(2000)
            backPressState = BackPress.Idle
        }
    }

    BackHandler(backPressState == BackPress.Idle) {
        backPressState = BackPress.InitialTouch
        showToast = true
    }

    val scope = rememberCoroutineScope()
    val locationClient = remember {
        LocationServices.getFusedLocationProviderClient(context)
    }
    val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    var hideDialog by remember {
        mutableStateOf(false)
    }

    var locationInfo by remember {
        mutableStateOf("")
    }
    var canOrder by remember {
        mutableStateOf(false)
    }
    if (canOrder) {
        if (!isGpsEnabled && !hideDialog) {
            AlertDialog(title = {
                Text(
                    text = "Enable GPS", style = TextStyle(
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        fontWeight = FontWeight.Bold
                    )
                )
            }, text = {
                Text("Please enable GPS to use this app.")
            }, onDismissRequest = { }, confirmButton = {
                Button(onClick = {
                    // Redirect the user to GPS settings
                    val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                    context.startActivity(intent)
                    hideDialog = true
                }) {
                    Text("Enable GPS")
                }
            })
        } else {
            LaunchedEffect(key1 = canOrder, block = {
                scope.launch(Dispatchers.IO) {
                    val priority = Priority.PRIORITY_HIGH_ACCURACY
                    val result =
                        locationClient.getCurrentLocation(priority, CancellationTokenSource().token)
                            .await()
                    result?.let { fetchedLocation ->
                        locationInfo = "${fetchedLocation.latitude},${fetchedLocation.longitude}"
                    }
                }
            })
        }


    }



    //
    val cameraPositionState = com.google.maps.android.compose.rememberCameraPositionState()
    var destination by remember {
        mutableStateOf(LatLng(0.0,0.0))
    }
    val markerState = rememberMarkerState(position = destination)


    Scaffold(modifier = Modifier, topBar = {}, bottomBar = {
        BottomAppBar {
            NavigationBarItem(label = { Text(text = "nepal") },
                selected = true,
                onClick = { },
                icon = {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "")
                })

            NavigationBarItem(selected = false, onClick = { }, icon = {
                Icon(imageVector = Icons.Default.Add, contentDescription = "")
            })

            NavigationBarItem(selected = false, onClick = { }, icon = {
                Icon(imageVector = Icons.Default.Add, contentDescription = "")
            })


        }
    }, content = {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
//                CurrentLocationScreen()
            RequestPermission(permission = Manifest.permission.ACCESS_FINE_LOCATION) {
                canOrder = it
            }

            if (locationInfo.isNotBlank()){
                LaunchedEffect(key1 = Unit, block ={
                    val userlatlng = locationInfo.split(",")
                    val position = CameraPosition.fromLatLngZoom(LatLng(userlatlng.first().toDouble(),userlatlng.last().toDouble()),16f)
                    cameraPositionState.position = position
                } )
            }
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                properties = remember {
                    MapProperties(
                        mapType = MapType.NORMAL,
                        isMyLocationEnabled = true,
                        mapStyleOptions = MapStyleOptions.loadRawResourceStyle(context,R.raw.map_style)
                    )
                },
                onMapClick = {
                    destination = it
                },
                onMapLongClick = {

                }

            ) {
                LaunchedEffect(destination) {
                    if (destination.latitude != 0.0) {
                        // Delay the marker update to ensure it occurs after the recomposition
                        delay(100)
                        markerState.position = destination
                    }
                }
                if (destination.latitude != 0.0){
                    Marker(
                        state = markerState,
                        draggable = true,
                        title = "Nepalgunj",
                        snippet = "Nepalgunj Momo bar",
                        icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE),
                        onInfoWindowLongClick = {
//                                viewModel.onEvent(MapEvent.OnInfoWindowLongClick(parkingSpot))
                        },
                        onClick = {
                            it.showInfoWindow()
                            true
                        }
                    )
                }

            }
            TextButton(
                onClick = { }, enabled = canOrder
            ) {
                Text(text = locationInfo)
            }
        }
    })

}
@Composable
fun getMapStyleOptions(): MapStyleOptions {
    return MapStyleOptions.loadRawResourceStyle(
        LocalContext.current,
        R.raw.map_style
    )
}
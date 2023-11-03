package com.chetan.orderdelivery.presentation.user.myorder

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.provider.Settings
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.chetan.orderdelivery.Destination
import com.chetan.orderdelivery.common.Constants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutUserCartScreen(
    navController: NavHostController,
    state: OutUserCartState,
    event: (event: MyOrderEvent) -> Unit
) {
    val cartList = listOf("Pizza", "Jhol MoMo", "Fried Momo", "Buff Momo", "Chicken Momo")

    val context = LocalContext.current
    val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    if (!isGpsEnabled) {
        val locationSettingsIntent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
        val requestCode = 0 // You can choose a unique request code
        val pendingIntent = PendingIntent.getActivity(
            context,
            requestCode,
            locationSettingsIntent,
            PendingIntent.FLAG_IMMUTABLE // Use FLAG_IMMUTABLE to comply with Android S+
        )
        pendingIntent.send()
    }

    Scaffold(
      topBar = {
            TopAppBar(modifier = Modifier.padding(horizontal = 5.dp), title = {
                Text(
                    text = "Food Description",
                    style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.primary)
                )
            }, navigationIcon = {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier,
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = ""
                    )
                }

            }, actions = {

            }

            )

        },
        content = {
        Column(modifier = Modifier.padding(it)) {
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = true, onCheckedChange = {})
                    Text(text = "Select All")
                }
                Button(shape = RoundedCornerShape(10.dp), onClick = {

                }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "")
                    Text(text = "Delete")
                }

            }
            Divider()
            LazyColumn(modifier = Modifier
                .weight(1f)
                .padding(end = 15.dp, start = 5.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                content = {
                    items(cartList) {
                        Row(
                            modifier = Modifier
                                .height(120.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(checked = false, onCheckedChange = {

                            })
                            Box(
                                modifier = Modifier.fillMaxSize(),
                            ) {
                                Card(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(top = 15.dp, start = 50.dp),
                                    elevation = CardDefaults.cardElevation(10.dp),
                                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary)
                                ) {
                                    Row(
                                        modifier = Modifier.padding(start = 60.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Column {
                                            Text(
                                                text = "Title",
                                                style = MaterialTheme.typography.headlineMedium.copy(

                                                )
                                            )

                                            Text(
                                                text = "Description asdf asd asdf aasd asd asdfa  asdf a",
                                                maxLines = 2,
                                                style = MaterialTheme.typography.bodyMedium.copy(
                                                    color = MaterialTheme.colorScheme.outline,
                                                    fontWeight = FontWeight.Bold
                                                ),
                                                overflow = TextOverflow.Ellipsis
                                            )
                                            Row(
                                                modifier = Modifier.fillMaxWidth(),
                                                horizontalArrangement = Arrangement.SpaceBetween,
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Text(
                                                    text = "Rs 200",
                                                    style = MaterialTheme.typography.bodyMedium.copy(
                                                        color = Color.Red,
                                                        fontWeight = FontWeight.SemiBold
                                                    )
                                                )
                                                Row(
                                                    modifier = Modifier.padding(
                                                        end = 10.dp, bottom = 5.dp
                                                    ),
                                                    verticalAlignment = Alignment.CenterVertically,
                                                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                                                ) {
                                                    Card(
                                                        modifier = Modifier.size(34.dp),
                                                        colors = CardDefaults.cardColors(
                                                            MaterialTheme.colorScheme.onPrimaryContainer
                                                        ),
                                                        elevation = CardDefaults.cardElevation(10.dp),
                                                    ) {
                                                        IconButton(onClick = { }) {
                                                            Icon(
                                                                imageVector = Icons.Default.Remove,
                                                                contentDescription = "Remove",
                                                                tint = Color.White
                                                            )
                                                        }
                                                    }
                                                    Text(
                                                        text = "2",
                                                        style = MaterialTheme.typography.headlineSmall
                                                    )
                                                    Card(
                                                        modifier = Modifier.size(34.dp),
                                                        colors = CardDefaults.cardColors(
                                                            MaterialTheme.colorScheme.primary
                                                        ),
                                                        elevation = CardDefaults.cardElevation(10.dp),
                                                    ) {
                                                        IconButton(onClick = { }) {
                                                            Icon(
                                                                imageVector = Icons.Default.Add,
                                                                contentDescription = "Add"
                                                            )
                                                        }
                                                    }

                                                }
                                            }
                                        }
                                    }
                                }
                                Card(
                                    modifier = Modifier
                                        .align(Alignment.TopStart)
                                        .size(100.dp),
                                    shape = CircleShape
                                ) {
                                    AsyncImage(
                                        model = Constants.testFoodUrl,
                                        contentDescription = "",
                                        contentScale = ContentScale.Crop

                                    )
                                }

                            }
                        }

                    }
                })
            Divider()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colorScheme.outline,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    ) {
                        append("Delivery: Rs 120\n")
                    }
                    withStyle(
                        style = SpanStyle(
                            fontSize = 18.sp, fontWeight = FontWeight.SemiBold
                        )
                    ) {
                        append("Total: ")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = Color.Red,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold

                        )
                    ) {
                        append("Rs")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = Color.Red,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    ) {
                        append("120")
                    }

                })
                Button(
                    onClick = {
                              navController.navigate(Destination.Screen.UserOrderCheckoutScreen.route)
                    }, shape = RoundedCornerShape(10.dp)
                ) {
                    Text(text = "Check out (2)")

                }
            }
            Divider()

        }
    })
}
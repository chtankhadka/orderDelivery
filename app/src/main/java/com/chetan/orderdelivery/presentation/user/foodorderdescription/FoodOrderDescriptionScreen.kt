package com.chetan.orderdelivery.presentation.user.foodorderdescription

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.provider.Settings
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.chetan.orderdelivery.presentation.common.components.dialogs.MessageDialog
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle

@SuppressLint("MissingPermission")
@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class,
    ExperimentalFoundationApi::class
)
@Composable
fun FoodOrderDescriptionScreen(
    onEvent: (event: FoodOrderDescriptionEvent) -> Unit,
    state: FoodOrderDescriptionState,
    navController: NavHostController,
    foodId: String
) {

    if (state.foodItemDetails.foodId.isBlank()){
        onEvent(FoodOrderDescriptionEvent.GetFoodItemDetails(foodId))
    }
    println("sdjklsdjkl;")
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


    val pagerImages = mutableListOf(
        state.foodItemDetails.faceImgUrl,
        state.foodItemDetails.supportImgUrl2,
        state.foodItemDetails.supportImgUrl3,
        state.foodItemDetails.supportImgUrl4,
    ).filter { it.isNotBlank() }

    val pagerState = rememberPagerState { pagerImages.size }
    val pageCount = pagerImages.size
    Scaffold(topBar = {
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
            Card(
                modifier = Modifier
                    .size(34.dp)
                    .clickable {

                    },
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),
                elevation = CardDefaults.cardElevation(10.dp),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(2.dp)
                ) {
                    Icon(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .size(20.dp),
                        imageVector = Icons.Default.ShoppingCart,
                        tint = Color.White,
                        contentDescription = ""
                    )
                    Text(
                        text = state.totalCartItem.toString(),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(end = 2.dp),
                        fontSize = 8.sp,
                        textAlign = TextAlign.Right,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

            }
        }

        )

    }, content = {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = 5.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            state.infoMsg?.let {
                MessageDialog(
                    message = it,
                    onDismissRequest = {
                        if (onEvent != null && state.infoMsg.isCancellable == true) {
                            onEvent(FoodOrderDescriptionEvent.DismissInfoMsg)
                        }
                    },
                    onPositive = { },
                    onNegative = {

                    })
            }


            if (pagerImages.size != 0) {
                HorizontalPager(
                    state = pagerState
                ) { page ->

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center)
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .size(150.dp)
                                .clip(shape = CircleShape),
                            contentScale = ContentScale.Crop,
                            model = pagerImages[page],
                            contentDescription = "",
                            alignment = Alignment.Center
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(pageCount) { iteration ->
                    val color =
                        if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .clip(CircleShape)
                            .background(color = color)
                            .size(20.dp)
                    )

                }
            }


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp)
            ) {
                Text(
                    text = state.foodItemDetails.foodName,
                    style = MaterialTheme.typography.headlineSmall
                )

                RatingBar(size = 15.dp,
                    value = state.foodItemDetails.foodRating,
                    spaceBetween = 2.dp,
                    style = RatingBarStyle.Default,
                    onValueChange = {

                    },
                    onRatingChanged = {

                    })
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Text(
                            text = "Rs. ${state.foodPrice * state.foodQuantity}",
                            style = MaterialTheme.typography.headlineLarge.copy(
                                fontWeight = FontWeight.ExtraBold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        )
                        Text(
                            text = "Rs. ${state.foodDiscount * state.foodQuantity}",

                            style = MaterialTheme.typography.titleMedium.copy(
                                color = MaterialTheme.colorScheme.outline,
                                textDecoration = TextDecoration.LineThrough
                            )
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        if (state.foodQuantity > 1) {
                            Card(
                                modifier = Modifier.size(34.dp),
                                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onPrimaryContainer),
                                elevation = CardDefaults.cardElevation(10.dp),
                            ) {
                                IconButton(
                                    onClick = {
                                        onEvent(FoodOrderDescriptionEvent.DecreaseQuantity)
                                    }) {
                                    Icon(
                                        imageVector = Icons.Default.Remove,
                                        contentDescription = "Remove",
                                        tint = Color.White
                                    )
                                }
                            }
                        }

                        Text(
                            text = "${state.foodQuantity}",
                            style = MaterialTheme.typography.headlineSmall
                        )
                        Card(
                            modifier = Modifier.size(34.dp),
                            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),
                            elevation = CardDefaults.cardElevation(10.dp),
                        ) {
                            IconButton(onClick = {
                                onEvent(FoodOrderDescriptionEvent.IncreaseQuantity)
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Add, contentDescription = "Add"
                                )
                            }
                        }

                    }
                }
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "About the food", style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = state.foodItemDetails.foodDetails,
                    style = MaterialTheme.typography.labelMedium.copy(MaterialTheme.colorScheme.outlineVariant)
                )


            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Button(
                    modifier = Modifier.weight(1f),
                    elevation = ButtonDefaults.buttonElevation(10.dp),
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.onPrimaryContainer),
                    onClick = {

                    },
                    enabled = true
                ) {
                    Text(text = "Order Now")
                }
                Button(
                    modifier = Modifier.weight(1f),
                    elevation = ButtonDefaults.buttonElevation(10.dp),
                    onClick = {
                        onEvent(FoodOrderDescriptionEvent.AddToCart(state.foodItemDetails.foodId))
                    },
                    enabled = true
                ) {
                    Text(text = "Add to Basket")
                }
            }
        }
    })
}


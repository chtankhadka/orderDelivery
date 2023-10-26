package com.chetan.orderdelivery.presentation.admin.orderdetails

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DeliveryDining
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.chetan.orderdelivery.R
import com.chetan.orderdelivery.common.Constants
import com.chetan.orderdelivery.presentation.common.components.LoadLottieAnimation
import com.chetan.orderdelivery.presentation.common.components.dialogs.MessageDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminOrderDetailScreen(
    navController: NavHostController,
    state: AdminOrderDetailState,
    event: (event: AdminOrderDetailEvent) -> Unit,
    user: String
) {

    var test by remember { mutableStateOf(0.01f) }
    val targetValue = 0.7f

    LaunchedEffect(targetValue) {
        test = targetValue
    }

    val animatedSize by animateFloatAsState(
        targetValue = test, animationSpec = tween(
            durationMillis = 2000, delayMillis = 100
        ), label = ""
    )

    state.user.ifBlank {
        event(AdminOrderDetailEvent.GetOrderDetails(user))
    }

    var orderId by remember {
        mutableStateOf("")
    }
    var showAlert by remember {
        mutableStateOf(false)
    }
    if (showAlert){
        AlertDialog(title = {
            Text(
                text = "Food Delivery", style = TextStyle(
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    fontWeight = FontWeight.Bold
                )
            )
        },
            text = {
                Text(text = "Is Delivered?")
            }, onDismissRequest = {

            }, confirmButton = {
                Button(
                    colors = ButtonDefaults.buttonColors(Constants.dark_primaryContainer),
                    onClick = {
                        showAlert = false
                        event(AdminOrderDetailEvent.Delivered(orderId))
                    }) {
                    Text(text = "Confirm")
                }
            }, dismissButton = {
                Button(colors = ButtonDefaults.buttonColors(Color.Red.copy(alpha = 0.7f)), onClick = {
                    showAlert = false
                }) {
                    Text(text = "Cancel")
                }
            })
    }

    Scaffold(topBar = {
        TopAppBar(modifier = Modifier.padding(horizontal = 5.dp), title = {
            Text(
                text = "Add Food",
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

        })
    }, content = {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .animateContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            state.infoMsg?.let {
                MessageDialog(message = it, onDismissRequest = {
                    if (event != null && state.infoMsg.isCancellable == true) {
                        event(AdminOrderDetailEvent.DismissInfoMsg)
                    }
                }, onPositive = { }, onNegative = {

                })
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Card(
                    modifier = Modifier.weight(animatedSize),
                    colors = CardDefaults.cardColors(Color.Blue),
                    shape = RoundedCornerShape(topEnd = 50.dp, bottomEnd = 50.dp),
                    elevation = CardDefaults.cardElevation(10.dp)
                ) {
                    Row(
                        Modifier
                            .fillMaxSize()
                            .padding(horizontal = 20.dp, vertical = 10.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .size(80.dp)
                                .clip(shape = CircleShape)
                                .background(color = Color.White)
                                .border(
                                    border = BorderStroke(
                                        width = 2.dp, color = Color.White
                                    )
                                ), model = Constants.testFoodUrl, contentDescription = ""
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "Chetan Khadka")
                    Row {
                        Icon(imageVector = Icons.Default.LocationOn, contentDescription = "")
                        Text(text = "Nepalgunj")
                    }
                }
            }
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = state.orderDetails.size.toString(),
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                        )
                        Text(
                            text = "Items", style = MaterialTheme.typography.headlineSmall
                        )
                    }
                    Divider(
                        modifier = Modifier
                            .height(50.dp)
                            .width(2.dp)
                    )
                    Divider(
                        modifier = Modifier
                            .height(50.dp)
                            .width(2.dp)
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                state.orderDetails.forEach { orders ->
                    Box {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 5.dp)
                                .padding(top = 40.dp), colors = CardDefaults.cardColors()
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Card(
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary)
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(5.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = orders.dateTime,
                                            style = MaterialTheme.typography.headlineMedium
                                        )

                                        Text(
                                            text = orders.distance,
                                            style = MaterialTheme.typography.headlineMedium
                                        )
                                    }


                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(5.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                            Text(
                                                text = orders.orderId,
                                                style = MaterialTheme.typography.headlineSmall
                                            )
                                        Column(
                                            horizontalAlignment = Alignment.End
                                        ) {
                                            Text(
                                                text = "2000",
                                                style = MaterialTheme.typography.headlineMedium
                                            )
                                            Text(
                                                text = "Discount 20",
                                                style = MaterialTheme.typography.headlineSmall.copy(
                                                    textDecoration = TextDecoration.LineThrough,
                                                    color = MaterialTheme.colorScheme.outline
                                                )
                                            )
                                        }
                                    }

                                }
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 20.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Order Details",
                                    textAlign = TextAlign.Left,
                                    style = MaterialTheme.typography.headlineMedium,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Card(
                                    modifier = Modifier.clickable {
                                        orderId = orders.orderId
                                        showAlert = true
                                    }, colors = CardDefaults.cardColors(Color.Transparent)
                                ) {
                                    LoadLottieAnimation(
                                        modifier = Modifier.size(80.dp), image = R.raw.delivered
                                    )
                                }
                            }
                            orders.orderList.forEach {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 20.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                                ) {
                                    Column(
                                        modifier = Modifier.weight(1f),
                                        verticalArrangement = Arrangement.spacedBy(10.dp)
                                    ) {
                                        Text(
                                            text = it.foodName,
                                            style = MaterialTheme.typography.headlineSmall
                                        )
                                        Text(
                                            text = "1 * Rs 200",
                                            style = MaterialTheme.typography.titleMedium.copy(
                                                fontWeight = FontWeight.SemiBold,
                                                color = MaterialTheme.colorScheme.outline
                                            )
                                        )
                                    }
                                    Text(
                                        text = "Rs 2000",
                                        style = MaterialTheme.typography.headlineMedium
                                    )
                                }
                                Divider(modifier = Modifier.padding(horizontal = 20.dp))
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = (100 - animatedSize * 100).dp),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(modifier = Modifier.size(60.dp), onClick = {}) {
                                Icon(
                                    modifier = Modifier.size(60.dp),
                                    imageVector = Icons.Default.DeliveryDining,
                                    contentDescription = "",
                                    tint = Color.Blue
                                )
                            }
                        }
                    }

                }
                Spacer(modifier = Modifier.height(15.dp))
            }
        }
    })
}

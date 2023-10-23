package com.chetan.orderdelivery.presentation.admin.orderdetails

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.LocationOn
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.chetan.orderdelivery.common.Constants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminOrderDetailScreen(
    navController: NavHostController,
    state: AdminOrderDetailState,
    event: (event: AdminOrderDetailEvent) -> Unit,
    user: String
) {

    state.user.ifBlank {
        event(AdminOrderDetailEvent.GetOrderDetails(user))
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
                .verticalScroll(rememberScrollState())
                .animateContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
//            state.infoMsg?.let {
//                MessageDialog(
//                    message = it,
//                    onDismissRequest = {
//                        if (event != null && state.infoMsg.isCancellable == true){
//                            event(AdminOrderDetailEvent.DismissInfoMsg)
//                        }
//                    },
//                    onPositive = {  },
//                    onNegative = {
//
//                    })
//            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Card(
                        modifier = Modifier
                            .weight(0.7f)
                            .fillMaxSize(),
                        colors = CardDefaults.cardColors(Color.Blue),
                        shape = RoundedCornerShape(topEnd = 50.dp, bottomEnd = 50.dp),
                        elevation = CardDefaults.cardElevation(10.dp)
                    ) {
                        Row(
                            Modifier
                                .fillMaxSize()
                                .padding(horizontal = 20.dp, vertical = 10.dp),
                            horizontalArrangement = Arrangement.End) {
                            AsyncImage(
                                modifier = Modifier
                                    .size(80.dp)
                                    .clip(shape = CircleShape)
                                    .background(color = Color.White)
                                    .border(
                                        border = BorderStroke(
                                            width = 2.dp,
                                            color = Color.White
                                        )
                                    ),
                                model = Constants.testFoodUrl,
                                contentDescription = "")
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
                       Icon(imageVector = Icons.Default.LocationOn, contentDescription = "" )
                        Text(text = "Nepalgunj")
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "2")
                    Text(text = "Items")
                }
                Divider(modifier = Modifier
                    .height(50.dp)
                    .width(2.dp))
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "5:20")
                    Text(text = "Time")
                }
                Divider(modifier = Modifier
                    .height(50.dp)
                    .width(2.dp))
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "ne")
                    Text(text = "en")
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            state.orderDetails.forEach { 
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(text = it.orderId)

                }
                it.orderList.forEach {
                    Text(text = it.foodName)
                }
            }
        }
    })
}

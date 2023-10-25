package com.chetan.orderdelivery.presentation.admin.dashboard.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.chetan.orderdelivery.Destination
import com.chetan.orderdelivery.presentation.common.components.dialogs.MessageDialog

@Composable
fun HomeScreen(
    navController: NavHostController, event: (event: AdminHomeEvent) -> Unit, state: AdminHomeState
) {
    Scaffold(topBar = {}, content = {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = 10.dp)
                .fillMaxSize(),
        ) {
            state.infoMsg?.let {
                MessageDialog(message = it, onDismissRequest = {
                    if (event != null && state.infoMsg.isCancellable == true) {
                        event(AdminHomeEvent.DismissInfoMsg)
                    }
                }, onPositive = { /*TODO*/ }) {

                }
            }
            LazyColumn(modifier = Modifier.fillMaxSize(), content = {
                items(state.orderList) { orders ->
                    Box(
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        Card(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 15.dp, start = 50.dp)
                                .clickable {
                                    navController.navigate(
                                        Destination.Screen.AdminOrderDetailScreen.route.replace(
                                            "{user}", orders.userMail
                                        )
                                    )
                                },
                            elevation = CardDefaults.cardElevation(10.dp),
                            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary)
                        ) {
                            Row(
                                modifier = Modifier.padding(start = 60.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Text(
                                        text = orders.userName.ifBlank { orders.googleUserName },
                                        style = MaterialTheme.typography.headlineMedium.copy(

                                        )
                                    )
                                    Text(
                                        text = orders.locationAddress,
                                        maxLines = 2,
                                        minLines = 2,
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

                                    }
                                }
                            }
                        }
                        Card(
                            modifier = Modifier.align(Alignment.TopStart),
                            shape = CircleShape
                        ) {
                            AsyncImage(
                                modifier = Modifier
                                    .size(100.dp)
                                    .border(
                                        border = BorderStroke(
                                            width = 2.dp, color = Color.White
                                        ), shape = CircleShape
                                    ),
                                model = orders.dbProfileUrl.ifBlank { orders.googleProfileUrl },
                                contentDescription = "",
                                contentScale = ContentScale.Crop

                            )
                        }
                        IconButton(
                            modifier = Modifier.align(Alignment.CenterEnd),
                            onClick = {
                                event(AdminHomeEvent.RemoveUser(orders.userMail))
                        }) {
                            Icon(
                                tint = MaterialTheme.colorScheme.error,
                                imageVector = Icons.Default.Delete, contentDescription = "" )
                        }


                    }
                }
            })
        }


    })


}

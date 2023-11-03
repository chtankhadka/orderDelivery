package com.chetan.orderdelivery.presentation.user.notification

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.chetan.orderdelivery.ui.theme.OrderDeliveryTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreen(
    nav: NavHostController,
    state: NotificationState,
    event: (event: NotificationEvent) -> Unit
) {
    Scaffold(topBar = {
        TopAppBar(modifier = Modifier.padding(horizontal = 5.dp), title = {
            Text(
                text = "Notification",
                style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.primary)
            )
        }, navigationIcon = {
            IconButton(onClick = {
                nav.popBackStack()
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
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = 10.dp)
                .fillMaxSize()
                .animateContentSize()
        ) {
            items(state.notificationList){ item ->
                val dismissState = rememberDismissState()
                SwipeToDismiss(
                    state = dismissState,
                    background = {
                        Box(
                            Modifier
                                .fillMaxSize()
                        ){
                            Icon(
                                modifier = Modifier
                                    .align(Alignment.CenterStart)
                                    .padding(start = 20.dp),
                                imageVector = Icons.Default.Delete, contentDescription = "",
                                tint = MaterialTheme.colorScheme.error)
                        }
                    },
                    directions = setOf(DismissDirection.StartToEnd),
                    dismissContent ={
                        Box(modifier = Modifier.padding(bottom = 5.dp)) {
                            Card(
                                modifier = Modifier
                                    .padding(top = 12.dp)
                                    .clickable {

                                    },
                                elevation = CardDefaults.cardElevation(10.dp),
                                shape = RoundedCornerShape(5.dp),
                                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 30.dp, end = 10.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = item.time, style = MaterialTheme.typography.titleMedium
                                    )
                                    Text(
//                                        text = "MyDate.differenceOfDates(notificationData.time, System.currentTimeMillis().toString())",
                                        text = "2 days ago",
                                        style = MaterialTheme.typography.bodySmall
                                    )

                                }
                                Text(
                                    modifier = Modifier.padding(start = 10.dp),
                                    text = item.body,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }

                            Icon(
                                modifier = Modifier.padding(start = 5.dp),
                                imageVector = if (item.readNotice) Icons.Default.NotificationsNone else Icons.Default.NotificationsActive,
                                contentDescription = "notification"
                            )
                        }

                    } )
                if (dismissState.isDismissed(direction = DismissDirection.StartToEnd)){
                    event(NotificationEvent.DeleteNotification(item.time))
                }
            }
        }
    })
}
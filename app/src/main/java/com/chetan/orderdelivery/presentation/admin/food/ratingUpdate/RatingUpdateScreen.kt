package com.chetan.orderdelivery.presentation.admin.food.ratingUpdate

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RatingUpdateScreen(
    navController: NavHostController,
    state: RatingUpdateState,
    onEvent: (event: RatingUpdateEvent) -> Unit
) {
    Scaffold(topBar = {
        TopAppBar(
            modifier = Modifier.padding(horizontal = 5.dp),
            title = {
                Text(
                    text = "Add Popular Food",
                    style = MaterialTheme.typography.headlineMedium.copy(color = MaterialTheme.colorScheme.onPrimaryContainer),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
            navigationIcon = {
                Icon(
                    modifier = Modifier,
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = ""
                )
            }
        )
    },
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .padding(horizontal = 5.dp)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                state.foodList.forEach {
                    Row {
                        Column {
                            Text(text = it.foodId)
                            Text(text = it.foodRating.toString())
                        }
                        Text(text = it.newFoodRating.toString())

                        Button(onClick = {
                            onEvent(RatingUpdateEvent.UpdateThis(it.foodId))
                        }) {
                            Text(text = "Update")
                        }
                       
                 
                    }
                }


            }
        })
}
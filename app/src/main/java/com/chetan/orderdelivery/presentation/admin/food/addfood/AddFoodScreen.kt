package com.chetan.orderdelivery.presentation.admin.food.addfood

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.chetan.orderdelivery.common.Constants
import com.chetan.orderdelivery.ui.theme.OrderDeliveryTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFoodScreen(
    navController: NavHostController?,
    state: AddFoodState,
    onEvent: (event: AddFoodEvent) -> Unit
) {

    var showAddDialog by remember {
        mutableStateOf(true)
    }
    var alertDialog by remember {
        mutableStateOf(false)
    }
    if (alertDialog) {
        AlertDialog(title = {
            Text(
                text = "Enable GPS", style = TextStyle(
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    fontWeight = FontWeight.Bold
                )
            )
        },
            text = {
                Text(text = "Please enable GPS for Ordering food.")
            },
            onDismissRequest = {

            },
            confirmButton = {
                Button(
                    colors = ButtonDefaults.buttonColors(Constants.dark_primaryContainer),
                    onClick = {
                    alertDialog = false
                    showAddDialog = false
                }) {
                    Text(text = "Confirm")
                }
            },
            dismissButton = {
                Button(
                    colors = ButtonDefaults.buttonColors(Color.Red.copy(alpha = 0.7f)),
                    onClick = {
                    alertDialog = false
                }) {
                    Text(text = "Cancel")
                }
            }
        )
    }
    if (showAddDialog) {
        Dialog(
            onDismissRequest = { }) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.onPrimary.copy(
                        alpha = 0.6f
                    )
                ),

                ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Card(
                        modifier = Modifier
                            .size(100.dp),
                        shape = CircleShape
                    ) {
                        AsyncImage(
                            modifier = Modifier.clickable { },
                            model = Constants.testFoodUrl,
                            contentDescription = "",
                            contentScale = ContentScale.Crop
                        )
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    OutlinedTextFieldAddFood(
                        foodLabel = "Food Name",
                        foodValue = state.foodName ,
                        onFoodValueChange = {
                            onEvent(AddFoodEvent.OnFoodNameChange(it))
                        },
                        foodMaxLine = 2)

                    OutlinedTextFieldAddFood(
                        foodLabel = "Food Details",
                        foodValue = state.foodDetails ,
                        onFoodValueChange = {
                            onEvent(AddFoodEvent.OnFoodDetailsChange(it))
                        },
                        foodMaxLine = 3)

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Button(
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(Constants.dark_primaryContainer),
                            onClick = {
                                alertDialog = true
                            }) {
                            Text(text = "Add", color = Color.White)
                        }
                        Button(
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(Color.Red.copy(alpha = 0.7f)),
                            onClick = {
                                showAddDialog = false
                            }) {
                            Text(text = "Cancel", color = Color.White)
                        }
                    }
                }

            }
        }
    }

    Scaffold(topBar = {
        TopAppBar(
            modifier = Modifier.padding(horizontal = 5.dp),
            title = {
                Text(
                    text = "Add Popular Food",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    ),
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
            },
            actions = {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        )
    },
        content = {
            LazyColumn(
                modifier = Modifier
                    .padding(it)
                    .padding(horizontal = 5.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(listOf("e", "k").size) {

                }

            }
        })
}

@Composable
@Preview
fun showThis() {
    OrderDeliveryTheme {
        AddFoodScreen(state = AddFoodState(), onEvent = {}, navController = null)
    }

}

@Composable
fun OutlinedTextFieldAddFood(
    modifier: Modifier = Modifier,
    foodLabel: String = "",
    foodValue : String,
    foodMaxLine : Int = 1,
    onFoodValueChange: (String) -> Unit,

){
    OutlinedTextField(
        modifier = modifier,
        textStyle = TextStyle.Default.copy(fontWeight = FontWeight.SemiBold),
        label = {
            Text(text = foodLabel, color = Color.White, fontWeight = FontWeight.SemiBold)
        },
        value = foodValue,
        onValueChange = {
            onFoodValueChange(it)
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.White,
            unfocusedIndicatorColor = Color.White,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White
        ),
        maxLines = foodMaxLine
    )
}

package com.chetan.orderdelivery.presentation.admin.addoffer

import androidx.compose.animation.animateContentSize
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.chetan.orderdelivery.common.Constants
import com.chetan.orderdelivery.presentation.admin.food.addfood.OutlinedTextFieldAddFood

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminAddOfferScreen(){
  Scaffold(topBar = {
        TopAppBar(modifier = Modifier.padding(horizontal = 5.dp), title = {
            Text(
                text = "Add Offer",
                style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.primary)
            )
        }, navigationIcon = {
            IconButton(onClick = {}) {
                Icon(
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier,
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = ""
                )
            }

        })
    },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .verticalScroll(rememberScrollState())
                    .animateContentSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
//                state.infoMsg?.let {
//                    MessageDialog(
//                        message = it,
//                        onDismissRequest = {
//                            if (onEvent != null && state.infoMsg.isCancellable == true){
//                                onEvent(AddFoodEvent.DismissInfoMsg)
//                            }
//                        },
//                        onPositive = {  },
//                        onNegative = {
//
//                        })
//                }

                Box(
                    modifier = Modifier.padding(10.dp)
                ) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            Color.Transparent
                        ),
                    ) {
                        Box {
                            Card(
                                modifier = Modifier.padding(top = 30.dp, end = 10.dp)
                            ) {
                                AsyncImage(
                                    modifier = Modifier.fillMaxWidth(),
                                    model = "state.faceImgUrl.imageUrl",
                                    contentDescription = "",
                                    contentScale = ContentScale.Crop
                                )
                            }
                            IconButton(modifier = Modifier.align(Alignment.TopEnd),
                                onClick = {
//                                photoPickerLauncher.launch(
//                                    PickVisualMediaRequest(
//                                        ActivityResultContracts.PickVisualMedia.ImageOnly
//                                    )
//                                )
                            }) {
                                Icon(
                                    modifier = Modifier.size(40.dp),
                                    imageVector = Icons.Default.AddAPhoto,
                                    contentDescription = null,
                                    tint = Constants.dark_primaryContainer
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(5.dp))
                        OutlinedTextFieldAddFood(
                            modifier = Modifier.fillMaxWidth(),
                            foodLabel = "Food Name",
                            foodValue = "state.foodName",
                            onFoodValueChange = {
//                                onEvent(AddFoodEvent.OnFoodNameChange(it))
                            },
                            foodMaxLine = 2
                        )
                        OutlinedTextFieldAddFood(
                            modifier = Modifier.fillMaxWidth(),
                            foodLabel = "Food Details",
                            foodValue = "state.foodDetails",
                            onFoodValueChange = {
//                                onEvent(AddFoodEvent.OnFoodDetailsChange(it))
                            },
                            foodMaxLine = 3
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            OutlinedTextFieldAddFood(
                                modifier = Modifier.weight(1f),
                                foodLabel = "Price",
                                foodValue = "state.foodPrice",
                                onFoodValueChange = {
//                                    onEvent(AddFoodEvent.OnFoodPriceChange(it))
                                },
                                foodMaxLine = 1,
                                foodKeyboard = KeyboardOptions.Default.copy(
                                    keyboardType = KeyboardType.Number
                                )
                            )

                            OutlinedTextFieldAddFood(
                                modifier = Modifier.weight(1f),
                                foodLabel = "Discount",
                                foodValue = "state.foodDiscountPrice",
                                onFoodValueChange = {
//                                    onEvent(AddFoodEvent.OnFoodDiscountChange(it))
                                },
                                foodMaxLine = 1,
                                foodKeyboard = KeyboardOptions.Default.copy(
                                    keyboardType = KeyboardType.Number
                                )
                            )
                        }
                        Spacer(modifier = Modifier.height(50.dp))
                        Button(modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(Constants.dark_primaryContainer),
                            onClick = {
//                                alertDialog = true
                            }) {
                            Text(text = "Add", color = Color.White)
                        }


                    }
                }
            }
        })
}

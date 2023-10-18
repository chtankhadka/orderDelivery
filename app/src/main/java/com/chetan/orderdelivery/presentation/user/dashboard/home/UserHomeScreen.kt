package com.chetan.orderdelivery.presentation.user.dashboard.home

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.chetan.orderdelivery.Destination
import com.chetan.orderdelivery.common.Constants
import com.chetan.orderdelivery.presentation.common.components.dialogs.MessageDialog
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle

data class TestItem(
    val image: String = "", val foodName: String = "", val rating: Float = 0f, val rate: String = ""
)

@Composable
fun UserHomeScreen(
    navController: NavHostController, state: UserHomeState, event: (event: UserHomeEvent) -> Unit
) {
    val listOfItem = listOf(
        TestItem(
            image = Constants.testFoodUrl,
            foodName = "Spicy Momo",
            rating = 4f,
            rate = "Rs 200",
        ), TestItem(
            image = Constants.testFoodUrl,
            foodName = "Spicy Momo",
            rating = 3f,
            rate = "Rs 20",
        ), TestItem(
            image = Constants.testFoodUrl,
            foodName = "Spicy Momo",
            rating = 5f,
            rate = "Rs 20000",
        ), TestItem(
            image = Constants.testFoodUrl,
            foodName = "Spicy Momo",
            rating = 1f,
            rate = "Rs 200",
        )
    )
    Scaffold(topBar = {},
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .padding(horizontal = 10.dp)
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize(),
            ) {
                state.infoMsg?.let {
                    MessageDialog(
                        message = it,
                        onDismissRequest = {
                            if (event != null && state.infoMsg.isCancellable == true) {
                                event(UserHomeEvent.DismissInfoMsg)
                            }
                        },
                        onPositive = { /*TODO*/ }) {

                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Food",
                        style = MaterialTheme.typography.headlineMedium.copy(color = MaterialTheme.colorScheme.onPrimaryContainer),
                        modifier = Modifier.padding(start = 5.dp)
                    )
                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(Color.Transparent)
                    ) {
                        Text(
                            text = "More", style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            ), modifier = Modifier.padding(start = 5.dp)
                        )
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
                LazyRow(horizontalArrangement = Arrangement.spacedBy(15.dp), content = {
                    items(state.allFoods) { foodItem ->
                        Column(
                            modifier = Modifier,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Card(
                                modifier = Modifier.clickable {
                                    navController.navigate(Destination.Screen.UserFoodOrderDescriptionScreen.route.replace(
                                        "{foodId}", foodItem.foodId
                                    ))
                                },
                                colors = CardDefaults.cardColors(Color.Transparent)
                            ) {
                                AsyncImage(
                                    modifier = Modifier
                                        .size(100.dp)
                                        .padding(5.dp)
                                        .clip(shape = CircleShape),
                                    model = foodItem.faceImgUrl,
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop
                                )
                                Text(
                                    modifier = Modifier.width(100.dp),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    text = foodItem.foodName,
                                    textAlign = TextAlign.Center,
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        fontWeight = FontWeight.SemiBold,
                                        textAlign = TextAlign.Center
                                    )
                                )
                            }


                        }
                    }
                })
                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Popular Food",
                        style = MaterialTheme.typography.headlineMedium.copy(color = MaterialTheme.colorScheme.onPrimaryContainer),
                        modifier = Modifier.padding(start = 5.dp)
                    )
                    Button(
                        onClick = { event(UserHomeEvent.More) },
                        colors = ButtonDefaults.buttonColors(Color.Transparent)
                    ) {
                        Text(
                            text = if (state.name.isNotEmpty()) {
                                state.name.first().item?.title ?: ""
                            } else {
                                ""
                            }, style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            ), modifier = Modifier.padding(start = 5.dp)
                        )
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }


                }
                LazyRow(horizontalArrangement = Arrangement.spacedBy(15.dp), content = {
                    items(listOfItem) { foodItem ->
                        Box(
                            modifier = Modifier
                                .padding(5.dp)
                                .width(150.dp)
                                .height(200.dp),
                        ) {
                            Card(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(vertical = 10.dp)
                                    .clickable {
                                        navController.navigate(Destination.Screen.UserFoodOrderDescriptionScreen.route)
                                    },
                                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onPrimary),
                                elevation = CardDefaults.cardElevation(10.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    AsyncImage(
                                        modifier = Modifier
                                            .padding(5.dp)
                                            .size(110.dp),
                                        model = foodItem.image,
                                        contentDescription = null,
                                        contentScale = ContentScale.Fit
                                    )
                                }

                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = foodItem.foodName,
                                    style = MaterialTheme.typography.headlineSmall.copy(
                                        textAlign = TextAlign.Center
                                    )
                                )
                                RatingBar(
                                    modifier = Modifier.align(Alignment.CenterHorizontally),
                                    size = 15.dp,
                                    value = foodItem.rating,
                                    style = RatingBarStyle.Default,
                                    onValueChange = {},
                                    onRatingChanged = {},
                                    numOfStars = 5,
                                    spaceBetween = 1.dp
                                )
                            }
                            Card(
                                modifier = Modifier.align(Alignment.BottomCenter),
                                elevation = CardDefaults.cardElevation(10.dp),
                                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary)
                            ) {
                                Text(
                                    text = foodItem.rate,
                                    modifier = Modifier.padding(horizontal = 15.dp),
                                    style = MaterialTheme.typography.titleSmall.copy(color = Color.White)
                                )
                            }
                            Card(
                                modifier = Modifier
                                    .align(Alignment.TopEnd)
                                    .padding(end = 5.dp),
                                elevation = CardDefaults.cardElevation(10.dp),
                                colors = CardDefaults.cardColors(Color.Transparent)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Favorite,
                                    tint = if (true) Color(179, 5, 5) else Color(179, 164, 164),
                                    contentDescription = "favourite"
                                )
                            }
                        }
                    }
                })
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "Offer",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.headlineMedium.copy(color = MaterialTheme.colorScheme.onPrimaryContainer),
                    modifier = Modifier.padding(start = 5.dp)
                )
                Box(modifier = Modifier.fillMaxWidth()) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(90.dp),
                        colors = CardDefaults.cardColors(Constants.dark_primaryContainer),
                    ) {

                    }
                    Row(modifier = Modifier.fillMaxWidth()) {
                        AsyncImage(
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .height(110.dp)
                                .weight(0.7f),
                            model = Constants.testFoodUrl,
                            contentDescription = ""
                        )
                        Text(
                            modifier = Modifier.weight(0.3f),
                            text = "Valentine Special Offer",
                            style = MaterialTheme.typography.titleMedium.copy(
                                color = Color.White,
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center
                            )
                        )
                    }

                }
                //Drinks
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Drinks",
                        style = MaterialTheme.typography.headlineMedium.copy(color = MaterialTheme.colorScheme.onPrimaryContainer),
                        modifier = Modifier.padding(start = 5.dp)
                    )
                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(Color.Transparent)
                    ) {
                        Text(
                            text = "More", style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            ), modifier = Modifier.padding(start = 5.dp)
                        )
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
                LazyRow(horizontalArrangement = Arrangement.spacedBy(15.dp), content = {
                    items(listOfItem) { foodItem ->
                        Column(
                            modifier = Modifier,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Card(
                                modifier = Modifier.clickable {
                                    navController.navigate(Destination.Screen.UserFoodOrderDescriptionScreen.route)
                                },
                                colors = CardDefaults.cardColors(Color.Transparent),
                            ) {
                                AsyncImage(
                                    modifier = Modifier
                                        .size(100.dp)
                                        .padding(5.dp),
                                    model = Constants.bottle,
                                    contentDescription = null,
                                    contentScale = ContentScale.FillWidth
                                )
                                Text(
                                    modifier = Modifier.width(100.dp),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    text = foodItem.foodName,
                                    textAlign = TextAlign.Center,
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        fontWeight = FontWeight.SemiBold,
                                        textAlign = TextAlign.Center
                                    )
                                )
                            }


                        }
                    }
                })
                Spacer(modifier = Modifier.height(5.dp))
            }


        })
}
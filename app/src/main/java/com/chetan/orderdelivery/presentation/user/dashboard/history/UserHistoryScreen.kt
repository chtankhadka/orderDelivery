package com.chetan.orderdelivery.presentation.user.dashboard.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import com.chetan.orderdelivery.common.Constants
import com.chetan.orderdelivery.presentation.common.utils.MyDate
import com.chetan.orderdelivery.presentation.user.dashboard.home.TestItem
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserHistoryScreen() {
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
    val cardSize = remember {
        mutableFloatStateOf(0f)
    }

    var rateDialog by remember {
        mutableStateOf(false)
    }
    var rateValue by remember {
        mutableFloatStateOf(0f)
    }
    var foodId by remember {
        mutableStateOf("")
    }
    if (rateDialog) {
        Dialog(
            onDismissRequest = { rateDialog = false },
            content = {
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .wrapContentSize(),
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onPrimary)
            ) {
                Column(
                    modifier = Modifier.padding(5.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(5.dp)) {
                    Text(text = "Rate it", style  = MaterialTheme.typography.headlineMedium)
                    AsyncImage(
                        modifier = Modifier.size(120.dp),
                        model = foodId,
                        contentDescription = ""
                    )
                    RatingBar(
                        size = 30.dp,
                        value = rateValue,
                        style = RatingBarStyle.Default,
                        onValueChange = {
                            rateValue = it
                        },
                        onRatingChanged = {},
                        numOfStars = 5,
                        spaceBetween = 1.dp
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Button(
                            modifier = Modifier.weight(1f),
                            elevation = ButtonDefaults.buttonElevation(10.dp),
                            onClick = {
                                      rateDialog = false
                            },
                        ) {
                            Text(text = "Rate it", color = Color.White)
                        }
                        Button(
                            modifier = Modifier.weight(1f),
                            elevation = ButtonDefaults.buttonElevation(10.dp),
                            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.error),
                            onClick = {
                                      rateDialog = false
                            },
                        ) {
                            Text(text = "Cancel", color = Color.White)
                        }
                    }
                }
            }
        })
    }

    Scaffold(topBar = {

    }, content = {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            LazyColumn(content = {
                items(listOfItem) {
                    Box(modifier = Modifier.padding(horizontal = 25.dp)) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 40.dp, bottom = 10.dp, end = 20.dp)
                                .height(150.dp)
                                .onGloballyPositioned {
                                    cardSize.value = Size(
                                        it.size.width.toFloat(), it.size.height.toFloat()
                                    ).width
                                },
                            shape = RoundedCornerShape(topStart = cardSize.value / 4),
                            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),
                            elevation = CardDefaults.cardElevation(10.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(horizontal = 10.dp),
                                verticalArrangement = Arrangement.Bottom
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                                        Text(
                                            text = "Hello",
                                            modifier = Modifier,
                                            style = MaterialTheme.typography.headlineSmall.copy(
                                                color = MaterialTheme.colorScheme.onPrimaryContainer
                                            )
                                        )
                                        RatingBar(
                                            size = 15.dp,
                                            value = 4f,
                                            style = RatingBarStyle.Default,
                                            onValueChange = {},
                                            onRatingChanged = {},
                                            numOfStars = 5,
                                            spaceBetween = 1.dp
                                        )
                                    }

                                    Text(
                                        modifier = Modifier,
                                        text = MyDate.differenceOfDates(
                                            System.currentTimeMillis().toString(),
                                            System.currentTimeMillis().toString()
                                        ), style = MaterialTheme.typography.bodySmall.copy(
                                            color = Color.White,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                }

                                Text(
                                    modifier = Modifier.fillMaxWidth(0.7f),
                                    text = "This is the dfa asdfad asd fasdf pasdfasdfa sdfasdf a asdf asdf a asd adf asdf f asdroduct in our hotel. Please try this once.",
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        fontWeight = FontWeight.SemiBold,
                                        color = MaterialTheme.colorScheme.outline
                                    ),
                                    maxLines = 3,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }


                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp)
                                .align(Alignment.TopCenter),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Card(
                                modifier = Modifier.clip(shape = CircleShape),
                                colors = CardDefaults.cardColors(Color.Transparent),
                                shape = CircleShape

                            ) {
                                AsyncImage(
                                    modifier = Modifier.size(120.dp),
                                    contentScale = ContentScale.Fit,
                                    model = Constants.testFoodUrl,
                                    contentDescription = ""
                                )
                            }
                            Card(
                                modifier = Modifier.padding(end = 5.dp),
                                elevation = CardDefaults.cardElevation(10.dp),
                                colors = CardDefaults.cardColors(Color.Transparent)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Favorite,
                                    tint = if (true) Color(
                                        179, 5, 5
                                    ) else Color(179, 164, 164),
                                    contentDescription = "favourite"
                                )
                            }
                        }
                        Button(modifier = Modifier.align(Alignment.BottomEnd),
                            shape = RoundedCornerShape(
                                bottomEndPercent = 100,
                                bottomStartPercent = 10,
                                topStartPercent = 100
                            ),
                            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.onPrimaryContainer),
                            onClick = {
                                foodId = Constants.bottle
                                rateDialog = true
                            }) {
                            Text(
                                text = "Rate this",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = FontWeight.SemiBold
                                )
                            )
                        }
                    }
                }
            })
        }
    })
}
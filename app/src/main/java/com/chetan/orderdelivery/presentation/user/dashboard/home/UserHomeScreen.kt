package com.chetan.orderdelivery.presentation.user.dashboard.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.chetan.orderdelivery.Destination
import com.chetan.orderdelivery.common.Constants
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle

data class testItem(
    val image: String = "", val foodName: String = "", val rating: Float = 0f, val rate: String = ""
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserHomeScreen(
    navController: NavHostController, state: UserHomeState, event: (event: UserHomeEvent) -> Unit
) {
    Scaffold(topBar = {}, content = {

        val listOfItem = listOf(
            testItem(
                image = Constants.testFoodUrl,
                foodName = "Spicy Momo",
                rating = 4f,
                rate = "Rs 200",
            ), testItem(
                image = Constants.testFoodUrl,
                foodName = "Spicy Momo",
                rating = 3f,
                rate = "Rs 20",
            ), testItem(
                image = Constants.testFoodUrl,
                foodName = "Spicy Momo",
                rating = 5f,
                rate = "Rs 20000",
            ), testItem(
                image = Constants.testFoodUrl,
                foodName = "Spicy Momo",
                rating = 1f,
                rate = "Rs 200",
            )
        )
        Column(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = 10.dp)
                .fillMaxSize()
        ) {
            Text(
                text = "Popular Food",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(start = 5.dp)
            )
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
                                .padding(bottom = 10.dp)
                                .clickable {
                                    navController.navigate(Destination.Screen.UserFoodOrderScreen.route)
                                },
                            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onPrimary),
                            elevation = CardDefaults.cardElevation(10.dp)
                        ) {
                            AsyncImage(
                                modifier = Modifier.padding(5.dp),
                                model = foodItem.image,
                                contentDescription = null,
                                contentScale = ContentScale.FillWidth
                            )
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
                                style = MaterialTheme.typography.titleSmall
                            )
                        }
                    }
                }
            })
        }
    })
}
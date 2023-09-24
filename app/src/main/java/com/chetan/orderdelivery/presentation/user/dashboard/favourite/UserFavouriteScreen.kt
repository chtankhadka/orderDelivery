package com.chetan.orderdelivery.presentation.user.dashboard.favourite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.chetan.orderdelivery.common.Constants
import com.chetan.orderdelivery.presentation.user.dashboard.home.TestItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserFavouriteScreen(){
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
    Scaffold(
        content = {
            Column(modifier = Modifier.padding(it)) {
                LazyColumn(content = {
                    items(listOfItem){
                        Box(modifier = Modifier.padding(horizontal = 50.dp)){
                            Card(modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 40.dp)
                                .height(300.dp)) {

                            }
                           Row(modifier = Modifier
                               .fillMaxWidth()
                               .padding(horizontal = 20.dp)
                               .align(Alignment.TopCenter),
                               verticalAlignment = Alignment.CenterVertically,
                               horizontalArrangement = Arrangement.SpaceEvenly
                               ) {
                               Card(
                                   colors = CardDefaults.cardColors(Color.Transparent),
                                   elevation = CardDefaults.cardElevation(5.dp),
                                   shape = CircleShape
                               ) {
                                   AsyncImage(
                                       modifier = Modifier.size(120.dp),
                                       contentScale = ContentScale.Crop,
                                       model = Constants.testFoodUrl,
                                       contentDescription = "")
                               }
                               Card(
                                   modifier = Modifier
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
                    }
                })
            }
        }
    )
}
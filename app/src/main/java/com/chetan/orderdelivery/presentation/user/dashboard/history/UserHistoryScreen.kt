package com.chetan.orderdelivery.presentation.user.dashboard.history

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserHistoryScreen() {

    Scaffold(
        topBar = {
                 
        },
        content = {
            Column(modifier = Modifier
                .padding(it)
                .fillMaxSize()) {

                Text(text = "History")
            }
        }
    )
}
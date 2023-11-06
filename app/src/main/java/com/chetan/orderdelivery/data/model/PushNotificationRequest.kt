package com.chetan.orderdelivery.data.model

data class PushNotificationRequest(
    val app_id :String = "7d7d5c3e-bdd2-4336-91c7-467ea0431cec",
//    val include_subscription_ids : List<String> = listOf(""),
    val include_player_ids : List<String> = emptyList(),
    val included_segments: List<String> = emptyList(),
    val contents: Map<String, String>,
    val headings: Map<String, String>
)
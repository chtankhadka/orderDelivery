package com.chetan.orderdelivery.data.model

data class PushNotificationRequest(
    val app_id :String = "4bc849f6-9a48-4194-bdce-a151a9c901b1",
//    val include_subscription_ids : List<String> = listOf(""),
    val include_player_ids : List<String>? = null ,
    val included_segments: List<String> ?= null,
    val contents: Map<String, String>,
    val name: String
)
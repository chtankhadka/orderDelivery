package com.chetan.orderdelivery.presentation.admin.dashboard.home

sealed interface AdminHomeEvent{
    data object DismissInfoMsg: AdminHomeEvent
    data object Test: AdminHomeEvent
}
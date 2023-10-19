package com.chetan.orderdelivery.presentation.admin.dashboard

sealed interface AdminDashboardEvent{
    data object ChangeDarkMode: AdminDashboardEvent
}
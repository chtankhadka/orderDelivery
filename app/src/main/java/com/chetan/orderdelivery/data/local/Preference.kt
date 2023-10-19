package com.chetan.orderdelivery.data.local

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.mutableStateOf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Preference constructor(
    val context : Context
) {
    @Inject
    constructor(
        application: Application
    ): this (
        application.applicationContext
    )

    private val sharedPreference: SharedPreferences =
        context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREFERENCE_NAME = "PREFERENCE_NAME"
        private const val USER_NAME = "USER_NAME"
        private const val IS_DARK_MODE = "IS_DARK_MODE"
    }

    var isDarkMode
        get() = mutableStateOf(sharedPreference.getBoolean(IS_DARK_MODE, false))
        set(value) {sharedPreference.edit().putBoolean(IS_DARK_MODE,value.value).apply()}





}
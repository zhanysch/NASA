package com.baish.skyscanner.data.local

import android.content.Context
import android.content.SharedPreferences

object PreferenceHelper {


    private const val PREFERENCE_NAME = "FitnessPreference"
    private const val IS_FIRST_LAUNCH = "IS_FIRST_LAUNCH"
    private const val FOR_REGISTR = "FOR_REGISTR"

    private const val PREFS_NAME = "com.baish.skyscanner"
    private const val TOKEN = "TOKEN"
    private lateinit var prefs: SharedPreferences

    private var preference: SharedPreferences? = null

    fun init(context: Context) {
        preference = context.getSharedPreferences(
            PREFERENCE_NAME, Context.MODE_PRIVATE)
    }

    fun saveToken(token: String) {
        prefs.edit().putString(TOKEN, token).apply()

    }

    fun geToken(): String {
        return prefs.getString(TOKEN, "") ?: ""

    }


    fun setIsFirstLaunch() {
        preference?.edit()?.putBoolean(
            IS_FIRST_LAUNCH, false)?.apply()
    }

    fun setIsLogetIn(bolean: Boolean) {
        preference?.edit()?.putBoolean(
            FOR_REGISTR, bolean)?.apply()
    }

    fun getLogetIn() = preference?.getBoolean(
        FOR_REGISTR, false) ?: false

    fun getIsFirstLaunch() = preference?.getBoolean(
        IS_FIRST_LAUNCH, true) ?: true
}
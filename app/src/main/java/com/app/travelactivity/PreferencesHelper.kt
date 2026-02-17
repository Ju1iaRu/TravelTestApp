package com.app.travelactivity

import android.content.Context
import android.content.SharedPreferences

class PreferencesHelper(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREFS_NAME = "TravelAppPrefs"
        private const val KEY_LOGGED_IN = "logged_in"
        private const val KEY_USERNAME = "username"
        private const val KEY_USERS = "users"
    }

    fun register(username: String, password: String): Boolean {
        val users = getUsers()
        if (users.containsKey(username)) {
            return false // Username already exists
        }
        users[username] = password
        saveUsers(users)
        return true
    }

    fun login(username: String, password: String): Boolean {
        val users = getUsers()
        if (users[username] == password) {
            prefs.edit().putBoolean(KEY_LOGGED_IN, true).putString(KEY_USERNAME, username).apply()
            return true
        }
        return false
    }

    fun logout() {
        prefs.edit().putBoolean(KEY_LOGGED_IN, false).remove(KEY_USERNAME).apply()
    }

    fun isLoggedIn(): Boolean {
        return prefs.getBoolean(KEY_LOGGED_IN, false)
    }

    fun getCurrentUsername(): String {
        return prefs.getString(KEY_USERNAME, "") ?: ""
    }

    private fun getUsers(): MutableMap<String, String> {
        val usersString = prefs.getString(KEY_USERS, "")
        if (usersString.isNullOrEmpty()) {
            return mutableMapOf()
        }
        val users = mutableMapOf<String, String>()
        usersString.split(";").forEach { entry ->
            val parts = entry.split(":")
            if (parts.size == 2) {
                users[parts[0]] = parts[1]
            }
        }
        return users
    }

    private fun saveUsers(users: Map<String, String>) {
        val usersString = users.entries.joinToString(";") { "${it.key}:${it.value}" }
        prefs.edit().putString(KEY_USERS, usersString).apply()
    }
}

package com.fork.spoonfeed.data.local

import android.content.Context
import javax.inject.Inject

class AutoLoginManager @Inject constructor(
    private val context: Context
) {
    private val sharedPreferences =
        context.getSharedPreferences(AUTO_LOGIN_DATA_FILE, Context.MODE_PRIVATE)

    fun setPlatform(value: String?) {
        sharedPreferences.edit()
            .putString(AUTO_LOGIN_DATA, value)
            .apply()
    }

    fun getPlatform(): String? {
        return sharedPreferences.getString(AUTO_LOGIN_DATA, null)
    }

    companion object {
        const val AUTO_LOGIN_DATA_FILE =
            "com.fork.spoonfeed.data.local AUTO_LOGIN_PLATFORM_FILE"
        const val AUTO_LOGIN_DATA = "com.fork.spoonfeed.data.local AUTO_LOGIN_PLATFORM"

        data class UserInfo(
            var refreshToken: String,
            var accessToken: String,
            var platform: String
        )
    }
}

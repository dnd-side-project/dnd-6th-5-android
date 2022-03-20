package com.fork.spoonfeed.data.local

import android.content.Context
import javax.inject.Inject

class AutoLoginPlatformManager @Inject constructor(
    private val context: Context
) {
    private val sharedPreferences =
        context.getSharedPreferences(AUTO_LOGIN_PLATFORM_FILE, Context.MODE_PRIVATE)

    fun setPlatform(value: String?) {
        sharedPreferences.edit()
            .putString(AUTO_LOGIN_PLATFORM, value)
            .apply()
    }

    fun getPlatform(): String? {
        return sharedPreferences.getString(AUTO_LOGIN_PLATFORM, null)
    }

    companion object {
        private const val AUTO_LOGIN_PLATFORM_FILE =
            "com.fork.spoonfeed.data.local AUTO_LOGIN_PLATFORM_FILE"
        private const val AUTO_LOGIN_PLATFORM = "com.fork.spoonfeed.data.local AUTO_LOGIN_PLATFORM"
    }
}

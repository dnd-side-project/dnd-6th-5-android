package com.fork.spoonfeed

import android.app.Application
import android.content.Context
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class SpoonFeedApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initLogger()
        appContext = this
        KakaoSdk.init(this, BuildConfig.KAKAO_NATIVE_KEY)

    }

    private fun initLogger() {
        Timber.plant(Timber.DebugTree())
    }

    companion object {
        var appContext: Context? = null
    }
}

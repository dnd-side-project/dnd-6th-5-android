package com.fork.spoonfeed

import android.app.Application
import android.content.Context
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SpoonFeedApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = this
        KakaoSdk.init(this, BuildConfig.KAKAO_NATIVE_KEY)

    }

    companion object {
        var appContext: Context? = null
    }
}

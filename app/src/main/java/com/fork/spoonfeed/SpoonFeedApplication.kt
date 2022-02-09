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
        KakaoSdk.init(this, getString(R.string.kakao_native_key))
    }

    companion object {
        var appContext: Context? = null
    }
}

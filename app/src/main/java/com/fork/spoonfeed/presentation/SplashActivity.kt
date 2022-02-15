package com.fork.spoonfeed.presentation

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.ActivitySplashBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil

class SplashActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
        startActivity()
    }

    private fun startActivity() {
        // 로그인 여부에 따라 Onboarding / Main으로 이동
        val intent = Intent(baseContext, MainActivity::class.java)
        Handler(Looper.getMainLooper())
            .postDelayed({
                startActivity(intent)
                finish()
            }, 2000)
    }
}

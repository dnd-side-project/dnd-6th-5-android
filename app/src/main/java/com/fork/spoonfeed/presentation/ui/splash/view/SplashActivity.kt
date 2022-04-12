package com.fork.spoonfeed.presentation.ui.splash.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.ActivitySplashBinding
import com.fork.spoonfeed.presentation.MainActivity
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.ui.onboarding.view.OnboardingActivity
import com.fork.spoonfeed.presentation.ui.splash.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
        viewModel.autoLogin()
        setObserver()
    }

    private fun setObserver() {
        viewModel.loginSuccess.observe(this) {
            if (it){
                moveMain()
            } else {
                moveOnboarding()
            }
        }
    }

    private fun moveOnboarding() {
        val intent = Intent(baseContext, OnboardingActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        startNextActivityWithHandling(intent)
    }

    private fun moveMain() {
        val intent = Intent(baseContext, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        startNextActivityWithHandling(intent)
    }

    private fun startNextActivityWithHandling(intent: Intent) {
        Handler(Looper.getMainLooper())
            .postDelayed({
                startActivity(intent)
                finish()
            }, 2000)
    }
}

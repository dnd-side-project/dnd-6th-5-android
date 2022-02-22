package com.fork.spoonfeed.presentation.ui.onboarding.view

import android.content.Intent
import android.os.Bundle
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.ActivityOnboardingBinding
import com.fork.spoonfeed.presentation.MainActivity
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.ui.onboarding.view.signup.SignupActivity

class OnboardingActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivityOnboardingBinding>(R.layout.activity_onboarding) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
        binding.mbOnboardingKakaoLogin.setOnClickListener {
            startSignupActivity()
        }
        binding.mbOnboardingNaverLogin.setOnClickListener {
            startSignupActivity()
        }
        binding.tvOnboardingSkip.setOnClickListener {
            startActivity(Intent(baseContext, MainActivity::class.java))
            finish()
        }
    }

    private fun startSignupActivity() {
        startActivity(Intent(baseContext, SignupActivity::class.java))
    }
}

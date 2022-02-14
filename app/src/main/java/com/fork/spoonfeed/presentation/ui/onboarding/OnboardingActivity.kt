package com.fork.spoonfeed.presentation.ui.onboarding

import android.os.Bundle
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.ActivityOnboardingBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil

class OnboardingActivity : BaseViewUtil.BaseAppCompatActivity<ActivityOnboardingBinding>(R.layout.activity_onboarding) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
    }
}

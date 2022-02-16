package com.fork.spoonfeed.presentation.ui.onboarding.signup

import android.os.Bundle
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.ActivitySignupBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil

class SignupActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivitySignupBinding>(R.layout.activity_signup) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {}
}

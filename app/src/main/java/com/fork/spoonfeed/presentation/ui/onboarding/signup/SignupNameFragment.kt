package com.fork.spoonfeed.presentation.ui.onboarding.signup

import android.os.Bundle
import android.view.View
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.FragmentSignupNameBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil

class SignupNameFragment :
    BaseViewUtil.BaseFragment<FragmentSignupNameBinding>(R.layout.fragment_signup_name) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun initView() {}
}

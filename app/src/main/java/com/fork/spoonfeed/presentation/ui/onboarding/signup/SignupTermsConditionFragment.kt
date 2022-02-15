package com.fork.spoonfeed.presentation.ui.onboarding.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.FragmentSignupTermsConditionBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil

class SignupTermsConditionFragment :
    BaseViewUtil.BaseFragment<FragmentSignupTermsConditionBinding>(R.layout.fragment_signup_terms_condition) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun initView() {}
}

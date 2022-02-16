package com.fork.spoonfeed.presentation.ui.onboarding.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.FragmentSignupFinishBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil

class SignupFinishFragment :
    BaseViewUtil.BaseFragment<FragmentSignupFinishBinding>(R.layout.fragment_signup_finish) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun initView() {}
}

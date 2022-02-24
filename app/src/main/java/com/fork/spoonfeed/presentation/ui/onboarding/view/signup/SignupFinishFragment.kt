package com.fork.spoonfeed.presentation.ui.onboarding.view.signup

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.FragmentSignupFinishBinding
import com.fork.spoonfeed.presentation.MainActivity
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupFinishFragment :
    BaseViewUtil.BaseFragment<FragmentSignupFinishBinding>(R.layout.fragment_signup_finish) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun initView() {
        setOnClickListener()
    }

    private fun setOnClickListener() {
        binding.mbSignupFinishStartSpoonfeed.setOnClickListener {
            startActivity(Intent(requireContext(), MainActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            })
        }
    }
}

package com.fork.spoonfeed.presentation.ui.onboarding.signup

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.FragmentSignupNameBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil

class SignupNameFragment :
    BaseViewUtil.BaseFragment<FragmentSignupNameBinding>(R.layout.fragment_signup_name) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun initView() {
        setOnClickListener()
    }

    private fun setOnClickListener() {
        // TODO 추후 뷰모델과 edittext 연결하여 enable 설정
        binding.mbSignupName.isEnabled = true
        binding.mbSignupName.backgroundTintList =
            ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.primary_blue))

        binding.mbSignupName.setOnClickListener {
            moveToNextLevel()
        }
    }

    fun moveToNextLevel() {
        (activity as SignupActivity).moveToNextLevel(SignupTermsConditionFragment())
    }
}

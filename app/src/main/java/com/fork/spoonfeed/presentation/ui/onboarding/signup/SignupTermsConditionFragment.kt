package com.fork.spoonfeed.presentation.ui.onboarding.signup

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.FragmentSignupTermsConditionBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil

class SignupTermsConditionFragment :
    BaseViewUtil.BaseFragment<FragmentSignupTermsConditionBinding>(R.layout.fragment_signup_terms_condition) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun initView() {
        setOnClickListener()
    }

    private fun setOnClickListener() {
        // TODO 추후 뷰모델과 checkbox 연결하여 enable 설정
        binding.mbSignupTermsCondition.isEnabled = true
        binding.mbSignupTermsCondition.backgroundTintList =
            ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.primary_blue))

        binding.mbSignupTermsCondition.setOnClickListener {
            moveToNextLevel()
        }
    }


    fun moveToNextLevel() {
        (activity as SignupActivity).moveToNextLevel(SignupFinishFragment())
    }
}

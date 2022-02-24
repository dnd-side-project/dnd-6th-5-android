package com.fork.spoonfeed.presentation.ui.onboarding.view.signup

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.FragmentSignupTermsConditionBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupTermsConditionFragment :
    BaseViewUtil.BaseFragment<FragmentSignupTermsConditionBinding>(R.layout.fragment_signup_terms_condition) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signUpTermsConditionFragment = this
        initView()
    }

    override fun initView() {
        setOnClickListener()
    }

    private fun setOnClickListener() {
        with(binding) {
            cbSignupTermsConditionAllAgree.setOnClickListener {
                if (cbSignupTermsConditionAllAgree.isChecked) {
                    setAllChecked()
                    setNextBtn()
                } else {
                    setAllUnChecked()
                    setNextBtn()
                }
            }
        }
    }

    fun setNextBtn() {
        with(binding) {
            if (isAllChecked()) {
                cbSignupTermsConditionAllAgree.isChecked = true
                mbSignupTermsCondition.isEnabled = true
                mbSignupTermsCondition.backgroundTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.primary_blue))

                mbSignupTermsCondition.setOnClickListener {
                    moveToNextLevel()
                }
            } else {
                cbSignupTermsConditionAllAgree.isChecked = false
                mbSignupTermsCondition.isEnabled = false
                mbSignupTermsCondition.backgroundTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.gray03))
            }
        }
    }

    fun moveToNextLevel() {
        (activity as SignupActivity).moveToNextLevel(SignupFinishFragment())
    }

    private fun setAllChecked() {
        with(binding) {
            cbSignupTermsConditionDigitalContent.isChecked = true
            cbSignupTermsConditionUserProtect.isChecked = true
            cbSignupTermsConditionPersonalInfo.isChecked = true
        }
    }

    private fun setAllUnChecked() {
        with(binding) {
            cbSignupTermsConditionDigitalContent.isChecked = false
            cbSignupTermsConditionUserProtect.isChecked = false
            cbSignupTermsConditionPersonalInfo.isChecked = false
        }
    }

    private fun isAllChecked(): Boolean {
        return binding.cbSignupTermsConditionDigitalContent.isChecked == true && binding.cbSignupTermsConditionUserProtect.isChecked == true && binding.cbSignupTermsConditionPersonalInfo.isChecked == true
    }
}

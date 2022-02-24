package com.fork.spoonfeed.presentation.ui.onboarding.view.signup

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.FragmentSignupNameBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.ui.onboarding.viewmodel.LoginViewModel
import com.fork.spoonfeed.presentation.util.shortToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupNameFragment : BaseViewUtil.BaseFragment<FragmentSignupNameBinding>(R.layout.fragment_signup_name) {
    private val loginViewModel: LoginViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginViewModel = loginViewModel
        binding.lifecycleOwner = this
        initView()
    }

    override fun initView() {
        nickNameTextChanged()
        setOnClickListener()
        setNickNameObserve()
    }

    private fun nickNameTextChanged() {
        binding.tietSignupName.addTextChangedListener {
            with(binding) {
                if (!tietSignupName.text.isNullOrEmpty()) {
                    mbSignupName.isEnabled = true
                    mbSignupName.backgroundTintList =
                        ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.primary_blue))
                } else {
                    mbSignupName.isEnabled = false
                    mbSignupName.backgroundTintList =
                        ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.gray03))
                }
            }
        }
    }

    private fun setOnClickListener() {
        binding.mbSignupName.setOnClickListener {
            loginViewModel.patchUserNickName()
        }
    }

    private fun setNickNameObserve() {
        loginViewModel.nickNameSetStatus.observe(this) { nickNameSetSuccess ->
            if (nickNameSetSuccess) {
                moveToNextLevel()
            }
        }
    }

    fun moveToNextLevel() {
        (activity as SignupActivity).moveToNextLevel(SignupTermsConditionFragment())
    }
}

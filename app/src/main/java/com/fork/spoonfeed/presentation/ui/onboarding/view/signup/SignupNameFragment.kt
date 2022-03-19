package com.fork.spoonfeed.presentation.ui.onboarding.view.signup

import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.FragmentSignupNameBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.ui.onboarding.viewmodel.LoginViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupNameFragment :
    BaseViewUtil.BaseFragment<FragmentSignupNameBinding>(R.layout.fragment_signup_name) {

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
                        ColorStateList.valueOf(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.primary_blue
                            )
                        )
                } else {
                    mbSignupName.isEnabled = false
                    mbSignupName.backgroundTintList =
                        ColorStateList.valueOf(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.gray03
                            )
                        )
                }
            }
        }
    }

    private fun setOnClickListener() {
        binding.mbSignupName.setOnClickListener {
            loginViewModel.setUserNickName(binding.tietSignupName.text.toString())
        }
    }

    private fun setNickNameObserve() {
        loginViewModel.isNameSpecial.observe(this) {
            if (!it) {
                Snackbar.make(binding.root, "존재하는 닉네임입니다.", Snackbar.LENGTH_SHORT).show()
            } else {
                loginViewModel.patchUserNickName(binding.tietSignupName.text.toString())
            }
        }
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

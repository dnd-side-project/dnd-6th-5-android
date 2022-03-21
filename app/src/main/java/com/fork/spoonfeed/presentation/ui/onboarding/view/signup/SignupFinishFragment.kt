package com.fork.spoonfeed.presentation.ui.onboarding.view.signup

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.FragmentSignupFinishBinding
import com.fork.spoonfeed.presentation.MainActivity
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.ui.onboarding.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupFinishFragment :
    BaseViewUtil.BaseFragment<FragmentSignupFinishBinding>(R.layout.fragment_signup_finish) {

    private val loginViewModel: LoginViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun initView() {
        setOnClickListener()
        setObserver()
    }

    private fun setOnClickListener() {
        binding.mbSignupFinishStartSpoonfeed.setOnClickListener {
            loginViewModel.patchUserNickName()
        }
    }

    private fun setObserver() {
        loginViewModel.nickNameSetStatus.observe(this) { nickNameSetSuccess ->
            if (nickNameSetSuccess) {
                startActivity(Intent(requireContext(), MainActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                })
            }
        }
    }
}

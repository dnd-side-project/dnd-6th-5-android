package com.fork.spoonfeed.presentation.ui.onboarding.view.signup

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.ActivitySignupBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.ui.onboarding.viewmodel.LoginViewModel
import com.fork.spoonfeed.presentation.util.addAndAddToBackStack
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivitySignupBinding>(R.layout.activity_signup) {
    private val loginViewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.loginViewModel = loginViewModel
        binding.lifecycleOwner = this
        initView()
    }

    override fun initView() {
        setOnClickListener()
    }

    private fun setOnClickListener() {
        binding.mtSignupTitle.setNavigationOnClickListener {
            if (supportFragmentManager.fragments.size == 1) {
                finish()
            } else {
                supportFragmentManager.popBackStack()
            }
        }
    }

    fun moveToNextLevel(newFragment: Fragment) {
        addAndAddToBackStack(R.id.fcv_signup, newFragment)
    }
}

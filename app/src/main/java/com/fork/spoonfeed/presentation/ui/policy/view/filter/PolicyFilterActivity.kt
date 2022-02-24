package com.fork.spoonfeed.presentation.ui.policy.view.filter

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.ActivityPolicyFilterBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.util.addAndAddToBackStack
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PolicyFilterActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivityPolicyFilterBinding>(R.layout.activity_policy_filter) {

    private val viewModel: PolicyFilterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
        setBackBtnClickListener()
    }

    private fun setBackBtnClickListener() {
        binding.mtPolicyFilterTitle.setNavigationOnClickListener {
            if (supportFragmentManager.fragments.size == 1) {
                finish()
            } else {
                supportFragmentManager.popBackStack()
            }
        }
    }

    fun moveToNextLevel(newFragment: Fragment) {
        addAndAddToBackStack(R.id.fcv_policy_filter, newFragment)
    }

    fun scrollToTop() {
        binding.svPolicyFilter.scrollTo(0, 0)
    }
}

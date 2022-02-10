package com.fork.spoonfeed.presentation.ui.policy.view.filter

import android.os.Bundle
import android.view.View
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.FragmentPolicyFilterLevelThreeBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil

class PolicyFilterLevelThreeFragment :
    BaseViewUtil.BaseFragment<FragmentPolicyFilterLevelThreeBinding>(R.layout.fragment_policy_filter_level_three) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onResume() {
        super.onResume()
        (activity as PolicyFilterActivity).scrollToTop()
    }

    override fun initView() {
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as PolicyFilterActivity).scrollToTop()
    }
}

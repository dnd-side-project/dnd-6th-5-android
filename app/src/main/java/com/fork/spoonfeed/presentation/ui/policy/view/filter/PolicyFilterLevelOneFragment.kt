package com.fork.spoonfeed.presentation.ui.policy.view.filter

import android.os.Bundle
import android.view.View
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.FragmentPolicyFilterLevelOneBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil

class PolicyFilterLevelOneFragment :
    BaseViewUtil.BaseFragment<FragmentPolicyFilterLevelOneBinding>(R.layout.fragment_policy_filter_level_one) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun initView() {
    }
}

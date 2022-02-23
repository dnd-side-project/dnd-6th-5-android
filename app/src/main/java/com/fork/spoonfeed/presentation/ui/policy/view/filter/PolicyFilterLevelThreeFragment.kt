package com.fork.spoonfeed.presentation.ui.policy.view.filter

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.fork.spoonfeed.presentation.ui.policy.view.filter.tooltip.NetWorthTooltipActivity
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.FragmentPolicyFilterLevelThreeBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.ui.policy.view.filter.tooltip.MedianIncomeTooltipActivity
import com.fork.spoonfeed.presentation.ui.policylist.view.PolicyListActivity

class PolicyFilterLevelThreeFragment :
    BaseViewUtil.BaseFragment<FragmentPolicyFilterLevelThreeBinding>(R.layout.fragment_policy_filter_level_three) {

    private val viewModel: PolicyFilterViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onResume() {
        super.onResume()
        (activity as PolicyFilterActivity).scrollToTop()
    }

    override fun initView() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.fragment = this

        setClickListener()
    }

    private fun setClickListener() {
        binding.tvPolicyFilterThreeMedianIncomeTooltip.setOnClickListener {
            startActivity(Intent(context, MedianIncomeTooltipActivity::class.java))
        }
        binding.tvPolicyFilterThreeNetWorthTooltip.setOnClickListener {
            startActivity(Intent(context, NetWorthTooltipActivity::class.java))
        }
    }

    fun moveToPolicyResult() {
        startActivity(Intent(context, PolicyListActivity::class.java))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as PolicyFilterActivity).scrollToTop()
        viewModel.clearLevelThree()
    }
}

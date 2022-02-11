package com.fork.spoonfeed.presentation.ui.policy.view.filter

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.FragmentPolicyFilterLevelThreeBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil

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
    }

    fun moveToPolicyResult() {
        // 맞춤 정책 확인 페이지로 이동
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as PolicyFilterActivity).scrollToTop()
    }
}

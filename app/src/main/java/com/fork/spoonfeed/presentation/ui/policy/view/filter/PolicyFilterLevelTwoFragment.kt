package com.fork.spoonfeed.presentation.ui.policy.view.filter

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.FragmentPolicyFilterLevelTwoBinding
import com.fork.spoonfeed.domain.model.CompanySize
import com.fork.spoonfeed.presentation.base.BaseViewUtil

class PolicyFilterLevelTwoFragment :
    BaseViewUtil.BaseFragment<FragmentPolicyFilterLevelTwoBinding>(R.layout.fragment_policy_filter_level_two) {

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
        setClickListener()
        setViewModelObserver()
    }

    private fun setClickListener() {
        binding.cgPolicyFilterTwoEmploymentAvailability.setOnCheckedChangeListener { _, checkedId ->
            val availability = when (checkedId) {
                binding.chipPolicyFilterLevelTwoEmploymentIncumbent.id -> true
                binding.chipPolicyFilterLevelTwoUnemployed.id -> false
                else -> return@setOnCheckedChangeListener
            }

            viewModel.setEmploymentAvailability(availability)
        }

        binding.cgPolicyFilterTwoCompanySize.setOnCheckedChangeListener { _, checkedId ->
            val companySize = when (checkedId) {
                binding.chipPolicyFilterLevelTwoCompanySmall.id -> CompanySize.SMALL
                binding.chipPolicyFilterLevelTwoCompanyMid.id -> CompanySize.MID
                binding.chipPolicyFilterLevelTwoCompanySelf.id -> CompanySize.SELF
                binding.chipPolicyFilterLevelTwoCompanyFounder.id -> CompanySize.FOUNDER
                binding.chipPolicyFilterLevelTwoCompanyNothing.id -> CompanySize.NOTHING
                else -> return@setOnCheckedChangeListener
            }

            viewModel.setCompanySize(companySize)
        }

        binding.mbPolicyFilterTwoNext.setOnClickListener {
            (activity as PolicyFilterActivity).moveToNextLevel(PolicyFilterLevelThreeFragment())
        }
    }

    private fun setViewModelObserver() {
        viewModel.employmentAvailability.observe(viewLifecycleOwner, {
            setNextButton(viewModel.isLevelTwoValid())
        })

        viewModel.companySize.observe(viewLifecycleOwner, {
            setNextButton(viewModel.isLevelTwoValid())
        })
    }

    private fun setNextButton(isActive: Boolean) {
        val color = if (isActive) R.color.primary_blue else R.color.gray03

        with(binding.mbPolicyFilterTwoNext) {
            isEnabled = isActive
            backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, color))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as PolicyFilterActivity).scrollToTop()
    }
}

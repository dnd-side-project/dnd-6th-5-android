package com.fork.spoonfeed.presentation.ui.policy.view.filter

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.FragmentPolicyFilterLevelThreeBinding
import com.fork.spoonfeed.domain.model.AnnualIncome
import com.fork.spoonfeed.domain.model.HomeOwnership
import com.fork.spoonfeed.domain.model.HouseHolderStatus
import com.fork.spoonfeed.domain.model.MedianIncome
import com.fork.spoonfeed.domain.model.NetWorth
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
        setClickListener()
        setViewModelObserver()
    }

    private fun setClickListener() {
        binding.cgPolicyFilterThreeMedianIncome.setOnCheckedChangeListener { _, checkedId ->
            val medianIncome = when (checkedId) {
                binding.chipPolicyFilterLevelThreeMedianIncomeUnder30.id -> MedianIncome.UNDER_30
                binding.chipPolicyFilterLevelThreeMedianIncomeUnder40.id -> MedianIncome.UNDER_40
                binding.chipPolicyFilterLevelThreeMedianIncomeUnder45.id -> MedianIncome.UNDER_45
                binding.chipPolicyFilterLevelThreeMedianIncomeUnder50.id -> MedianIncome.UNDER_50
                binding.chipPolicyFilterLevelThreeMedianIncomeUnder72.id -> MedianIncome.UNDER_72
                binding.chipPolicyFilterLevelThreeMedianIncomeUnder100.id -> MedianIncome.UNDER_100
                binding.chipPolicyFilterLevelThreeMedianIncomeNothing.id -> MedianIncome.NOTHING
                binding.chipPolicyFilterLevelThreeMedianIncomePrivate.id -> MedianIncome.PRIVATE
                else -> return@setOnCheckedChangeListener
            }

            viewModel.setMedianIncome(medianIncome)
        }

        binding.cgPolicyFilterThreeAnnualIncome.setOnCheckedChangeListener { _, checkedId ->
            val annualIncome = when (checkedId) {
                binding.chipPolicyFilterLevelThreeAnnualIncomeCoupleUnder2.id -> AnnualIncome.COUPLE_UNDER_2
                binding.chipPolicyFilterLevelThreeAnnualIncomeCoupleUnder5.id -> AnnualIncome.COUPLE_UNDER_5
                binding.chipPolicyFilterLevelThreeAnnualIncomeSingleUnder3.id -> AnnualIncome.SINGLE_UNDER_3
                binding.chipPolicyFilterLevelThreeAnnualIncomeSingleUnder35.id -> AnnualIncome.SINGLE_UNDER_3_5
                binding.chipPolicyFilterLevelThreeAnnualIncomeNothing.id -> AnnualIncome.NOTHING
                binding.chipPolicyFilterLevelThreeAnnualIncomePrivate.id -> AnnualIncome.PRIVATE
                else -> return@setOnCheckedChangeListener
            }

            viewModel.setAnnualIncome(annualIncome)
        }

        binding.cgPolicyFilterThreeNetWorth.setOnCheckedChangeListener { _, checkedId ->
            val netWorth = when (checkedId) {
                binding.chipPolicyFilterLevelThreeNetWorthUnder292.id -> NetWorth.UNDER_2_92
                binding.chipPolicyFilterLevelThreeNetWorthOver292.id -> NetWorth.OVER_2_92
                binding.chipPolicyFilterLevelThreeNetWorthPrivate.id -> NetWorth.PRIVATE
                else -> return@setOnCheckedChangeListener
            }

            viewModel.setNetWorth(netWorth)
        }

        binding.cgPolicyFilterThreeHouseHolderStatus.setOnCheckedChangeListener { _, checkedId ->
            val houseHolderStatus = when (checkedId) {
                binding.chipPolicyFilterLevelThreeHouseHolderStatusOwner.id -> HouseHolderStatus.OWNER
                binding.chipPolicyFilterLevelThreeHouseHolderStatusMember.id -> HouseHolderStatus.MEMBER
                binding.chipPolicyFilterLevelThreeHouseHolderStatusPrivate.id -> HouseHolderStatus.PRIVATE
                else -> return@setOnCheckedChangeListener
            }

            viewModel.setHouseHolderStatus(houseHolderStatus)
        }

        binding.cgPolicyFilterThreeHomeOwnership.setOnCheckedChangeListener { _, checkedId ->
            val homeOwnership = when (checkedId) {
                binding.chipPolicyFilterLevelThreeHomeOwnershipHomeless.id -> HomeOwnership.HOMELESS
                binding.chipPolicyFilterLevelThreeHomeOwnershipOwner.id -> HomeOwnership.OWNER
                binding.chipPolicyFilterLevelThreeHomeOwnershipPrivate.id -> HomeOwnership.PRIVATE
                else -> return@setOnCheckedChangeListener
            }

            viewModel.setHomeOwnership(homeOwnership)
        }

        binding.cbPolicyFilterSave.setOnClickListener {
            viewModel.setSaveData((it as CheckBox).isChecked)
        }

        binding.mbPolicyFilterThreeNext.setOnClickListener {
            // 맞춤 정책 결과 화면으로 이동
        }
    }

    private fun setViewModelObserver() {
        viewModel.medianIncome.observe(viewLifecycleOwner, {
            setNextButton(viewModel.isLevelThreeValid())
        })

        viewModel.annualIncome.observe(viewLifecycleOwner, {
            setNextButton(viewModel.isLevelThreeValid())
        })

        viewModel.netWorth.observe(viewLifecycleOwner, {
            setNextButton(viewModel.isLevelThreeValid())
        })

        viewModel.houseHolderStatus.observe(viewLifecycleOwner, {
            setNextButton(viewModel.isLevelThreeValid())
        })

        viewModel.homeOwnership.observe(viewLifecycleOwner, {
            setNextButton(viewModel.isLevelThreeValid())
        })
    }

    private fun setNextButton(isActive: Boolean) {
        val color = if (isActive) R.color.primary_blue else R.color.gray03

        with(binding.mbPolicyFilterThreeNext) {
            isEnabled = isActive
            backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, color))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as PolicyFilterActivity).scrollToTop()
    }
}

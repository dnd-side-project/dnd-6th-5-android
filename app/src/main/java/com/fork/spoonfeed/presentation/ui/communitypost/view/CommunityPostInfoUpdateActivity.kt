package com.fork.spoonfeed.presentation.ui.communitypost.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.ActivityCommunityPostInfoUpdateBinding
import com.fork.spoonfeed.domain.model.AnnualIncome
import com.fork.spoonfeed.domain.model.CompanySize
import com.fork.spoonfeed.domain.model.HomeOwnership
import com.fork.spoonfeed.domain.model.HouseHolderStatus
import com.fork.spoonfeed.domain.model.MedianIncome
import com.fork.spoonfeed.domain.model.NetWorth
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.ui.communitypost.viewmodel.CommunityPostInfoUpdateViewModel
import com.fork.spoonfeed.presentation.util.showFloatingDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommunityPostInfoUpdateActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivityCommunityPostInfoUpdateBinding>(R.layout.activity_community_post_info_update) {

    private val communityPostInfoUpdateViewModel: CommunityPostInfoUpdateViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
        communityPostInfoUpdateViewModel.getUserData()
        setObserver()
    }

    private fun setObserver() {
        communityPostInfoUpdateViewModel.initialUserInfo.observe(this) {
            if (it.age != null) {
                initAgeField(it?.age)
                initMarriageField(it.maritalStatus)
                initWorkStatus(it.workStatus)
                initCompanyScale(it.companyScale)
                initMedianIncome(it.medianIncome)
                initAnnualIncome(it.annualIncome)
                initAsset(it.asset)
                initHouseOwner(it.isHouseOwner)
                initHasHouse(it.hasHouse)
                setOnClickListener()
            } else {
                showGotoPolicyDialog()
            }
        }
    }

    private fun initAgeField(age: String?) {
        binding.etCommunityPostInfoUpdateAgeYear.setText(age?.substring(0..3))
        binding.etCommunityPostInfoUpdateAgeMonth.setText(age?.substring(5..6))
        binding.etCommunityPostInfoUpdateAgeDay.setText(age?.substring(8..9))
    }

    private fun initMarriageField(maritalStatus: String) {
        binding.cgCommunityPostInfoUpdateMarriage.check(
            when (maritalStatus) {
                "기혼" -> binding.chipCommunityPostInfoUpdateMarriageTrue.id
                else -> binding.chipCommunityPostInfoUpdateMarriageFalse.id
            }
        )
    }

    private fun initWorkStatus(workStatus: String) {
        binding.cgCommunityPostInfoUpdateEmploymentAvailability.check(
            when (workStatus) {
                "재직자" -> binding.chipCommunityPostInfoUpdateEmploymentIncumbent.id
                else -> binding.chipCommunityPostInfoUpdateUnemployed.id
            }
        )
    }

    private fun initCompanyScale(companyScale: String) {
        binding.cgCommunityPostInfoUpdateCompanySize.check(
            when (companyScale) {
                CompanySize.SMALL.value -> binding.chipCommunityPostInfoUpdateCompanySmall.id
                CompanySize.MID.value -> binding.chipCommunityPostInfoUpdateCompanyMid.id
                CompanySize.SELF.value -> binding.chipCommunityPostInfoUpdateCompanySelf.id
                CompanySize.FOUNDER.value -> binding.chipCommunityPostInfoUpdateCompanyFounder.id
                else -> binding.chipCommunityPostInfoUpdateCompanyNothing.id
            }
        )
    }

    private fun initMedianIncome(medianIncome: String) {
        binding.cgCommunityPostInfoUpdateMedianIncome.check(
            when (medianIncome) {
                MedianIncome.UNDER_30.value -> binding.chipCommunityPostInfoUpdateMedianIncomeUnder30.id
                MedianIncome.UNDER_40.value -> binding.chipCommunityPostInfoUpdateMedianIncomeUnder40.id
                MedianIncome.UNDER_45.value -> binding.chipCommunityPostInfoUpdateMedianIncomeUnder45.id
                MedianIncome.UNDER_50.value -> binding.chipCommunityPostInfoUpdateMedianIncomeUnder50.id
                MedianIncome.UNDER_72.value -> binding.chipCommunityPostInfoUpdateMedianIncomeUnder72.id
                MedianIncome.UNDER_100.value -> binding.chipCommunityPostInfoUpdateMedianIncomeUnder100.id
                MedianIncome.NOTHING.value -> binding.chipCommunityPostInfoUpdateMedianIncomeNothing.id
                else -> binding.chipCommunityPostInfoUpdateMedianIncomePrivate.id
            }
        )
    }

    private fun initAnnualIncome(annualIncome: String) {
        binding.cgCommunityPostInfoUpdateAnnualIncome.check(
            when (annualIncome) {
                AnnualIncome.COUPLE_UNDER_2.value -> binding.chipCommunityPostInfoUpdateAnnualIncomeCoupleUnder2.id
                AnnualIncome.COUPLE_UNDER_5.value -> binding.chipCommunityPostInfoUpdateAnnualIncomeCoupleUnder5.id
                AnnualIncome.SINGLE_UNDER_3.value -> binding.chipCommunityPostInfoUpdateAnnualIncomeSingleUnder3.id
                AnnualIncome.SINGLE_UNDER_3_5.value -> binding.chipCommunityPostInfoUpdateAnnualIncomeSingleUnder35.id
                AnnualIncome.NOTHING.value -> binding.chipCommunityPostInfoUpdateAnnualIncomeNothing.id
                else -> binding.chipCommunityPostInfoUpdateAnnualIncomePrivate.id
            }
        )
    }

    private fun initAsset(asset: String) {
        binding.cgCommunityPostInfoUpdateNetWorth.check(
            when (asset) {
                NetWorth.UNDER_2_92.value -> binding.chipCommunityPostInfoUpdateNetWorthUnder292.id
                NetWorth.OVER_2_92.value -> binding.chipCommunityPostInfoUpdateNetWorthOver292.id
                else -> binding.chipCommunityPostInfoUpdateNetWorthPrivate.id
            }
        )
    }

    private fun initHouseOwner(houseOwner: String) {
        binding.cgCommunityPostInfoUpdateHouseHolderStatus.check(
            when (houseOwner) {
                HouseHolderStatus.MEMBER.value -> binding.chipCommunityPostInfoUpdateHouseHolderStatusMember.id
                HouseHolderStatus.OWNER.value -> binding.chipCommunityPostInfoUpdateHouseHolderStatusOwner.id
                else -> binding.chipCommunityPostInfoUpdateHouseHolderStatusPrivate.id
            }
        )
    }

    private fun initHasHouse(hasHouse: String) {
        binding.cgCommunityPostInfoUpdateHomeOwnership.check(
            when (hasHouse) {
                HomeOwnership.HOMELESS.value -> binding.chipCommunityPostInfoUpdateHomeOwnershipHomeless.id
                HomeOwnership.OWNER.value -> binding.chipCommunityPostInfoUpdateHomeOwnershipOwner.id
                else -> binding.chipCommunityPostInfoUpdateHomeOwnershipPrivate.id
            }
        )
    }

    private fun setOnClickListener() {
        binding.cgCommunityPostInfoUpdateMedianIncome.setOnCheckedChangeListener { _, checkedId ->
            communityPostInfoUpdateViewModel.updateMedianIncome(
                when (checkedId) {
                    binding.chipCommunityPostInfoUpdateMedianIncomeUnder30.id -> MedianIncome.UNDER_30.value
                    binding.chipCommunityPostInfoUpdateMedianIncomeUnder40.id -> MedianIncome.UNDER_40.value
                    binding.chipCommunityPostInfoUpdateMedianIncomeUnder45.id -> MedianIncome.UNDER_45.value
                    binding.chipCommunityPostInfoUpdateMedianIncomeUnder50.id -> MedianIncome.UNDER_50.value
                    binding.chipCommunityPostInfoUpdateMedianIncomeUnder72.id -> MedianIncome.UNDER_72.value
                    binding.chipCommunityPostInfoUpdateMedianIncomeUnder100.id -> MedianIncome.UNDER_100.value
                    binding.chipCommunityPostInfoUpdateMedianIncomeNothing.id -> MedianIncome.NOTHING.value
                    else -> MedianIncome.PRIVATE.value
                }
            )
        }
        binding.cgCommunityPostInfoUpdateAnnualIncome.setOnCheckedChangeListener { _, checkedId ->
            communityPostInfoUpdateViewModel.updateAnnualIncome(
                when (checkedId) {
                    binding.chipCommunityPostInfoUpdateAnnualIncomeCoupleUnder2.id -> AnnualIncome.COUPLE_UNDER_2.value
                    binding.chipCommunityPostInfoUpdateAnnualIncomeCoupleUnder5.id -> AnnualIncome.COUPLE_UNDER_5.value
                    binding.chipCommunityPostInfoUpdateAnnualIncomeSingleUnder3.id -> AnnualIncome.SINGLE_UNDER_3.value
                    binding.chipCommunityPostInfoUpdateAnnualIncomeSingleUnder35.id -> AnnualIncome.SINGLE_UNDER_3_5.value
                    binding.chipCommunityPostInfoUpdateAnnualIncomeNothing.id -> AnnualIncome.NOTHING.value
                    else -> AnnualIncome.PRIVATE.value
                }
            )
        }
        binding.cgCommunityPostInfoUpdateNetWorth.setOnCheckedChangeListener { _, checkedId ->
            communityPostInfoUpdateViewModel.updateAsset(
                when (checkedId) {
                    binding.chipCommunityPostInfoUpdateNetWorthUnder292.id -> NetWorth.UNDER_2_92.value
                    binding.chipCommunityPostInfoUpdateNetWorthOver292.id -> NetWorth.OVER_2_92.value
                    else -> NetWorth.PRIVATE.value
                }
            )
        }
        binding.cgCommunityPostInfoUpdateHouseHolderStatus.setOnCheckedChangeListener { _, checkedId ->
            communityPostInfoUpdateViewModel.updateHouseOwner(
                when (checkedId) {
                    binding.chipCommunityPostInfoUpdateHouseHolderStatusMember.id -> HouseHolderStatus.MEMBER.value
                    binding.chipCommunityPostInfoUpdateHouseHolderStatusOwner.id -> HouseHolderStatus.OWNER.value
                    else -> HouseHolderStatus.PRIVATE.value
                }
            )
        }
        binding.cgCommunityPostInfoUpdateHomeOwnership.setOnCheckedChangeListener { _, checkedId ->
            communityPostInfoUpdateViewModel.updateHasHouse(
                when (checkedId) {
                    binding.chipCommunityPostInfoUpdateHomeOwnershipHomeless.id -> HomeOwnership.HOMELESS.value
                    binding.chipCommunityPostInfoUpdateHomeOwnershipOwner.id -> HomeOwnership.OWNER.value
                    else -> HomeOwnership.PRIVATE.value
                }
            )
        }
        binding.mtCommunityPostInfoUpdateTitle.setNavigationOnClickListener {
            setResult(RESULT_OK, Intent().apply {
                putExtra(INFO_UPDATE_RESULT, communityPostInfoUpdateViewModel.updatedUserData)
            })
            finish()
        }
        binding.mbCommunityPostInfoUpdate.setOnClickListener {
            setResult(RESULT_OK, Intent().apply {
                putExtra(INFO_UPDATE_RESULT, communityPostInfoUpdateViewModel.updatedUserData)
            })
            finish()
        }
    }

    @SuppressLint("ResourceType")
    private fun showGotoPolicyDialog() {
        val dialog = this.showFloatingDialog(R.layout.dialog_goto_policy)
        val inputBtn = dialog.findViewById<Button>(R.id.tv_dialog_goto_policy_input)
        val cancelBtn = dialog.findViewById<Button>(R.id.tv_dialog_goto_policy_cancel)

        inputBtn.setOnClickListener {
            dialog.dismiss()
        }

        cancelBtn.setOnClickListener {
            dialog.dismiss()
        }
    }

    companion object {
        const val INFO_UPDATE_RESULT = "com.fork.spoonfeed.presentation.ui.communitypost.view"
    }
}

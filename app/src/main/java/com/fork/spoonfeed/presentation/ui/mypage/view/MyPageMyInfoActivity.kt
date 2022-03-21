package com.fork.spoonfeed.presentation.ui.mypage.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Button
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import com.fork.spoonfeed.R
import com.fork.spoonfeed.data.UserData
import com.fork.spoonfeed.databinding.ActivityMyPageMyInfoBinding
import com.fork.spoonfeed.domain.model.*
import com.fork.spoonfeed.presentation.MainActivity
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.ui.communitypost.viewmodel.CommunityPostInfoUpdateViewModel
import com.fork.spoonfeed.presentation.ui.mypage.viewmodel.MyPageMyInfoViewModel
import com.fork.spoonfeed.presentation.util.showFloatingDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPageMyInfoActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivityMyPageMyInfoBinding>(R.layout.activity_my_page_my_info) {

    private val myPageMyInfoViewModel: MyPageMyInfoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
        binding.lifecycleOwner = this
        binding.viewModel = myPageMyInfoViewModel
        binding.activity = this
        myPageMyInfoViewModel.getUserData()
        myPageMyInfoViewModel.getNickName()
        setObserver()
        setChipOnClickListener()
        setOnClickListener()
        nickNameTextChanged()

    }

    private fun setObserver() {
        myPageMyInfoViewModel.initialUserInfo.observe(this) {
            if (it.age != null) {
                initAgeField(it.age)
                initMarriageField(it.maritalStatus)
                initWorkStatus(it.workStatus)
                initCompanyScale(it.companyScale)
                initMedianIncome(it.medianIncome)
                initAnnualIncome(it.annualIncome)
                initAsset(it.asset)
                initHouseOwner(it.isHouseOwner)
                initHasHouse(it.hasHouse)
                patchUserFilterObserve()
            } else {
                showGotoPolicyDialog()
            }
        }
    }

    private fun initAgeField(age: String?) {
        binding.etMypageMyInfoUpdateAgeYear.setText(age?.substring(0..3))
        binding.etMypageMyInfoUpdateAgeMonth.setText(age?.substring(5..6))
        binding.etMypageMyInfoUpdateAgeDay.setText(age?.substring(8..9))
    }

    private fun initMarriageField(maritalStatus: String) {
        binding.cgMypageMyInfoUpdateMarriage.check(
            when (maritalStatus) {
                "기혼" -> binding.chipMypageMyInfoUpdateMarriageTrue.id
                else -> binding.chipMypageMyInfoUpdateMarriageFalse.id
            }
        )
    }

    private fun initWorkStatus(workStatus: String) {
        binding.cgMypageMyInfoUpdateEmploymentAvailability.check(
            when (workStatus) {
                "재직자" -> binding.chipMypageMyInfoUpdateEmploymentIncumbent.id
                else -> binding.chipMypageMyInfoUpdateUnemployed.id
            }
        )
    }

    private fun initCompanyScale(companyScale: String) {
        binding.cgMypageMyInfoUpdateCompanySize.check(
            when (companyScale) {
                CompanySize.SMALL.value -> binding.chipMypageMyInfoUpdateCompanySmall.id
                CompanySize.MID.value -> binding.chipMypageMyInfoUpdateCompanyMid.id
                CompanySize.SELF.value -> binding.chipMypageMyInfoUpdateCompanySelf.id
                CompanySize.FOUNDER.value -> binding.chipMypageMyInfoUpdateCompanyFounder.id
                else -> binding.chipMypageMyInfoUpdateCompanyNothing.id
            }
        )
    }

    private fun initMedianIncome(medianIncome: String) {
        binding.cgMypageMyInfoUpdateMedianIncome.check(
            when (medianIncome) {
                MedianIncome.UNDER_30.value -> binding.chipMypageMyInfoUpdateMedianIncomeUnder30.id
                MedianIncome.UNDER_40.value -> binding.chipMypageMyInfoUpdateMedianIncomeUnder40.id
                MedianIncome.UNDER_45.value -> binding.chipMypageMyInfoUpdateMedianIncomeUnder45.id
                MedianIncome.UNDER_50.value -> binding.chipMypageMyInfoUpdateMedianIncomeUnder50.id
                MedianIncome.UNDER_72.value -> binding.chipMypageMyInfoUpdateMedianIncomeUnder72.id
                MedianIncome.UNDER_100.value -> binding.chipMypageMyInfoUpdateMedianIncomeUnder100.id
                MedianIncome.NOTHING.value -> binding.chipMypageMyInfoUpdateMedianIncomeNothing.id
                else -> binding.chipMypageMyInfoUpdateMedianIncomePrivate.id
            }
        )
    }

    private fun initAnnualIncome(annualIncome: String) {
        binding.cgMypageMyInfoUpdateAnnualIncome.check(
            when (annualIncome) {
                AnnualIncome.COUPLE_UNDER_2.value -> binding.chipMypageMyInfoUpdateAnnualIncomeCoupleUnder2.id
                AnnualIncome.COUPLE_UNDER_5.value -> binding.chipMypageMyInfoUpdateAnnualIncomeCoupleUnder5.id
                AnnualIncome.SINGLE_UNDER_3.value -> binding.chipMypageMyInfoUpdateAnnualIncomeSingleUnder3.id
                AnnualIncome.SINGLE_UNDER_3_5.value -> binding.chipMypageMyInfoUpdateAnnualIncomeSingleUnder35.id
                AnnualIncome.NOTHING.value -> binding.chipMypageMyInfoUpdateAnnualIncomeNothing.id
                else -> binding.chipMypageMyInfoUpdateAnnualIncomePrivate.id
            }
        )
    }

    private fun initAsset(asset: String) {
        binding.cgMypageMyInfoUpdateNetWorth.check(
            when (asset) {
                NetWorth.UNDER_2_92.value -> binding.chipMypageMyInfoUpdateNetWorthUnder292.id
                NetWorth.OVER_2_92.value -> binding.chipMypageMyInfoUpdateNetWorthOver292.id
                else -> binding.chipMypageMyInfoUpdateNetWorthPrivate.id
            }
        )
    }

    private fun initHouseOwner(houseOwner: String) {
        binding.cgMypageMyInfoUpdateHouseHolderStatus.check(
            when (houseOwner) {
                HouseHolderStatus.MEMBER.value -> binding.chipMypageMyInfoUpdateHouseHolderStatusMember.id
                HouseHolderStatus.OWNER.value -> binding.chipMypageMyInfoUpdateHouseHolderStatusOwner.id
                else -> binding.chipMypageMyInfoUpdateHouseHolderStatusPrivate.id
            }
        )
    }

    private fun initHasHouse(hasHouse: String) {
        binding.cgMypageMyInfoUpdateHomeOwnership.check(
            when (hasHouse) {
                HomeOwnership.HOMELESS.value -> binding.chipMypageMyInfoUpdateHomeOwnershipHomeless.id
                HomeOwnership.OWNER.value -> binding.chipMypageMyInfoUpdateHomeOwnershipOwner.id
                else -> binding.chipMypageMyInfoUpdateHomeOwnershipPrivate.id
            }
        )
    }


    private fun setOnClickListener() {
        binding.ivMypageMyInfoUpdateNameClear.setOnClickListener {
            binding.etMypageMyInfoUpdateName.setText("")
        }

        binding.mbMypageMyInfoUpdate.setOnClickListener {
            myPageMyInfoViewModel.patchUserFilter()
            myPageMyInfoViewModel.patchUserNickName(binding.etMypageMyInfoUpdateName.text.toString())
        }
    }

    private fun setChipOnClickListener() {
        binding.cgMypageMyInfoUpdateMedianIncome.setOnCheckedChangeListener { _, checkedId ->
            myPageMyInfoViewModel.updateMedianIncome(
                when (checkedId) {
                    binding.chipMypageMyInfoUpdateMedianIncomeUnder30.id -> MedianIncome.UNDER_30.value
                    binding.chipMypageMyInfoUpdateMedianIncomeUnder40.id -> MedianIncome.UNDER_40.value
                    binding.chipMypageMyInfoUpdateMedianIncomeUnder45.id -> MedianIncome.UNDER_45.value
                    binding.chipMypageMyInfoUpdateMedianIncomeUnder50.id -> MedianIncome.UNDER_50.value
                    binding.chipMypageMyInfoUpdateMedianIncomeUnder72.id -> MedianIncome.UNDER_72.value
                    binding.chipMypageMyInfoUpdateMedianIncomeUnder100.id -> MedianIncome.UNDER_100.value
                    binding.chipMypageMyInfoUpdateMedianIncomeNothing.id -> MedianIncome.NOTHING.value
                    else -> MedianIncome.PRIVATE.value
                }
            )
        }
        binding.cgMypageMyInfoUpdateAnnualIncome.setOnCheckedChangeListener { _, checkedId ->
            myPageMyInfoViewModel.updateAnnualIncome(
                when (checkedId) {
                    binding.chipMypageMyInfoUpdateAnnualIncomeCoupleUnder2.id -> AnnualIncome.COUPLE_UNDER_2.value
                    binding.chipMypageMyInfoUpdateAnnualIncomeCoupleUnder5.id -> AnnualIncome.COUPLE_UNDER_5.value
                    binding.chipMypageMyInfoUpdateAnnualIncomeSingleUnder3.id -> AnnualIncome.SINGLE_UNDER_3.value
                    binding.chipMypageMyInfoUpdateAnnualIncomeSingleUnder35.id -> AnnualIncome.SINGLE_UNDER_3_5.value
                    binding.chipMypageMyInfoUpdateAnnualIncomeNothing.id -> AnnualIncome.NOTHING.value
                    else -> AnnualIncome.PRIVATE.value
                }
            )
        }
        binding.cgMypageMyInfoUpdateNetWorth.setOnCheckedChangeListener { _, checkedId ->
            myPageMyInfoViewModel.updateAsset(
                when (checkedId) {
                    binding.chipMypageMyInfoUpdateNetWorthUnder292.id -> NetWorth.UNDER_2_92.value
                    binding.chipMypageMyInfoUpdateNetWorthOver292.id -> NetWorth.OVER_2_92.value
                    else -> NetWorth.PRIVATE.value
                }
            )
        }
        binding.cgMypageMyInfoUpdateHouseHolderStatus.setOnCheckedChangeListener { _, checkedId ->
            myPageMyInfoViewModel.updateHouseOwner(
                when (checkedId) {
                    binding.chipMypageMyInfoUpdateHouseHolderStatusMember.id -> HouseHolderStatus.MEMBER.value
                    binding.chipMypageMyInfoUpdateHouseHolderStatusOwner.id -> HouseHolderStatus.OWNER.value
                    else -> HouseHolderStatus.PRIVATE.value
                }
            )
        }
        binding.cgMypageMyInfoUpdateHomeOwnership.setOnCheckedChangeListener { _, checkedId ->
            myPageMyInfoViewModel.updateHasHouse(
                when (checkedId) {
                    binding.chipMypageMyInfoUpdateHomeOwnershipHomeless.id -> HomeOwnership.HOMELESS.value
                    binding.chipMypageMyInfoUpdateHomeOwnershipOwner.id -> HomeOwnership.OWNER.value
                    else -> HomeOwnership.PRIVATE.value
                }
            )
        }
    }

    private fun patchUserFilterObserve() {
        myPageMyInfoViewModel.isPatchUserInfoSuccess.observe(this) { isPatchUserInfoSuccess ->
            if (isPatchUserInfoSuccess)
                finish()
        }
    }

    private fun nickNameTextChanged() {
        with(binding) {
            etMypageMyInfoUpdateName.addTextChangedListener {
                val updatedNickName = etMypageMyInfoUpdateName.text.toString()
                if (!updatedNickName.isNullOrEmpty()) {
                    myPageMyInfoViewModel.setPatchUserNickNameVaild()
                } else {
                    myPageMyInfoViewModel.setPatchUserNickNameInVaild()
                }
            }
        }
    }

    @SuppressLint("ResourceType")
    private fun showGotoPolicyDialog() {
        val dialog = this.showFloatingDialog(R.layout.dialog_goto_policy)
        val inputBtn = dialog.findViewById<Button>(R.id.tv_dialog_goto_policy_input)
        val cancelBtn = dialog.findViewById<Button>(R.id.tv_dialog_goto_policy_cancel)

        inputBtn.setOnClickListener {
            dialog.dismiss()
            finish()
        }

        cancelBtn.setOnClickListener {
            dialog.dismiss()
            finish()
        }
    }
}


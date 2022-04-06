package com.fork.spoonfeed.presentation.ui.communitypost.view

import android.os.Bundle
import androidx.activity.viewModels
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.ActivityUserReportReasonBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.ui.communitypost.viewmodel.CommunityPostViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserReportReasonActivity : BaseViewUtil.BaseAppCompatActivity<ActivityUserReportReasonBinding>(R.layout.activity_user_report_reason) {
    private val communityPostViewModel: CommunityPostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        binding.userReportActivity = this
        binding.communityPostViewModel = communityPostViewModel
        initView()
    }

    override fun initView() {
        setObserve()
        setBackBtnClickListener()
    }

    private fun setObserve() {
        communityPostViewModel.isReportReasonValid.observe(this) {
            if (!it) setClickable()
        }
    }

    private fun setClickable() {
        with(binding) {
            if (!mbUserReport.isCheckable)
                ctvUserReportReasonOne.isClickable = true
            ctvUserReportReasonTwo.isClickable = true
            ctvUserReportReasonThree.isClickable = true
            ctvUserReportReasonFour.isClickable = true
            ctvUserReportReasonFive.isClickable = true
            ctvUserReportReasonSix.isClickable = true
        }
    }

    private fun setBackBtnClickListener() {
        binding.mtUserReport.setNavigationOnClickListener {
            finish()
        }
    }

    companion object {
        const val REPORT_REASON_ONE = 1
        const val REPORT_REASON_TWO = 2
        const val REPORT_REASON_THREE = 3
        const val REPORT_REASON_FOUR = 4
        const val REPORT_REASON_FIVE = 5
        const val REPORT_REASON_SIX = 6
    }
}
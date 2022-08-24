package com.fork.spoonfeed.presentation.ui.policylist.view

import android.os.Bundle
import androidx.activity.viewModels
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.ActivityApplyQualificationBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.ui.policylist.viewmodel.DetailInfoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ApplyQualificationActivity : BaseViewUtil.BaseAppCompatActivity<ActivityApplyQualificationBinding>(R.layout.activity_apply_qualification) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
    }

    override fun initView() {
        setBackBtnClickListener()
        setApplyQualification()
    }

    private fun setBackBtnClickListener() {
        binding.ivApplyQualificationsBack.setOnClickListener {
            finish()
        }
    }

    private fun setApplyQualification() {
        with(binding) {
            tvApplyQualificationsAgeContent.text = intent.getStringExtra(DetailInfoActivity.LIMITED_AGE)
            tvApplyQualificationResidenceAndIncomeContent.text = intent.getStringExtra(DetailInfoActivity.LIMITED_AREA_ASSET)
            tvApplyQualificationSpecializationFieldContent.text = intent.getStringExtra(DetailInfoActivity.SPECIALIZATION)
        }
    }
}

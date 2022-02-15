package com.fork.spoonfeed.presentation.ui.policylist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.ActivityApplyQualificationBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil

class ApplyQualificationActivity : BaseViewUtil.BaseAppCompatActivity<ActivityApplyQualificationBinding>(R.layout.activity_apply_qualification) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
        setBackBtnClickListener()
    }

    private fun setBackBtnClickListener() {
        binding.ivApplyQualificationsBack.setOnClickListener {
            finish()
        }
    }
}
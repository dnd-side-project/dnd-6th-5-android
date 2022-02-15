package com.fork.spoonfeed.presentation.ui.policylist.view

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.ActivityApplyExplainBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil

class ApplyExplainActivity : BaseViewUtil.BaseAppCompatActivity<ActivityApplyExplainBinding>(R.layout.activity_apply_explain) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
        setBackBtnClickListener()
    }

    private fun setBackBtnClickListener() {
        binding.ivApplyExplainBack.setOnClickListener {
            finish()
        }
    }
}
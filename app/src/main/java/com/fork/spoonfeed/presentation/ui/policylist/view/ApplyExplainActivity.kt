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
        setApplyExplain()
    }

    private fun setBackBtnClickListener() {
        binding.ivApplyExplainBack.setOnClickListener {
            finish()
        }
    }

    private fun setApplyExplain() {
        with(binding) {
            tvApplyExplainContent.text = intent.getStringExtra("content")
            tvApplyExplainDetailInfoNoteContent.text = intent.getStringExtra("otherInfo")
            tvApplyExplainRestrictionSubjectContent.text = intent.getStringExtra("limitedTarget")
            tvApplyExplainSupportScaleContent.text = intent.getStringExtra("supportScale")
        }
    }
}
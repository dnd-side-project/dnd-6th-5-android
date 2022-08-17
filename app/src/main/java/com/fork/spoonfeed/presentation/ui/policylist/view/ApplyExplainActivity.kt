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
            tvApplyExplainContent.text = intent.getStringExtra(DetailInfoActivity.CONTENT)
            tvApplyExplainDetailInfoNoteContent.text = intent.getStringExtra(DetailInfoActivity.OTHER_INFO)
            tvApplyExplainRestrictionSubjectContent.text = intent.getStringExtra(DetailInfoActivity.LIMITED_TARGET)
            tvApplyExplainSupportScaleContent.text = intent.getStringExtra(DetailInfoActivity.SUPPORT_SCALE)
        }
    }
}
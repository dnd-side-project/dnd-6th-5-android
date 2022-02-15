package com.fork.spoonfeed.presentation.ui.policy.view.filter.tooltip

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.ActivityMedianIncomeTooltipBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil

class MedianIncomeTooltipActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivityMedianIncomeTooltipBinding>(R.layout.activity_median_income_tooltip) {

    private val medianIncomeUrl =
        "http://www.mohw.go.kr/upload/viewer/skin/doc.html?fn=1628123391435_20210805092951.pdf&rs=/upload/viewer/result/202202/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
        setClickListener()
    }

    private fun setClickListener() {
        binding.mtMedianIncomeTooltipTitle.setNavigationOnClickListener {
            finish()
        }
        binding.mbMedianIncomeTooltipClose.setOnClickListener {
            finish()
        }
        binding.mbMedianIncomeTooltipShortcut.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(medianIncomeUrl)))
        }
    }
}

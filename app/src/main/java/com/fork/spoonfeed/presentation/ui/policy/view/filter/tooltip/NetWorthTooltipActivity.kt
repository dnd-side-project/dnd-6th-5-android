package com.fork.spoonfeed.presentation.ui.policy.view.filter.tooltip

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.ActivityNetWorthTooltipBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil

class NetWorthTooltipActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivityNetWorthTooltipBinding>(R.layout.activity_net_worth_tooltip) {

    private val netWorthUrl =
        "https://txsi.hometax.go.kr/docs_new/customer/dictionary/view.jsp?word=&word_id=873"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
        setClickListener()
    }

    private fun setClickListener() {
        binding.mtNetWorthTooltipTitle.setNavigationOnClickListener {
            finish()
        }
        binding.mbNetWorthTooltipClose.setOnClickListener {
            finish()
        }
        binding.mbNetWorthTooltipShortcut.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(netWorthUrl)))
        }
    }
}

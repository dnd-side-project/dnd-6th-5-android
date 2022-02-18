package com.fork.spoonfeed.presentation.ui.mypage.view

import android.os.Bundle
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.ActivitySecessionBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.util.setBackBtnClickListener

class SecessionActivity : BaseViewUtil.BaseAppCompatActivity<ActivitySecessionBinding>(R.layout.activity_secession) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
        this.setBackBtnClickListener(binding.ivSecessionBack)
    }
}
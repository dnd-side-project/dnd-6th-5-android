package com.fork.spoonfeed.presentation.ui.mypage.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.ActivityNoticeBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil

class NoticeActivity : BaseViewUtil.BaseAppCompatActivity<ActivityNoticeBinding>(R.layout.activity_notice) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
    }
}
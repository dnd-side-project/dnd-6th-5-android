package com.fork.spoonfeed.presentation.ui.mypage.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.ActivityDetailInfoBinding
import com.fork.spoonfeed.databinding.ActivityNoticeDetailBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil

class NoticeDetailActivity : BaseViewUtil.BaseAppCompatActivity<ActivityNoticeDetailBinding>(R.layout.activity_notice_detail) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
        setBackBtnClickListener()
    }

    private fun setBackBtnClickListener() {
        binding.ivNoticeDetailBack.setOnClickListener {
            finish()
        }
    }
}
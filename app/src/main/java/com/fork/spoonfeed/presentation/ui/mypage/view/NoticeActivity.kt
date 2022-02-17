package com.fork.spoonfeed.presentation.ui.mypage.view

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.ActivityNoticeBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.ui.mypage.adapter.NoticeAdapter
import com.fork.spoonfeed.presentation.ui.mypage.adapter.NoticeResponseData
import com.fork.spoonfeed.presentation.ui.policylist.adapter.PolicyListResponseData

class NoticeActivity : BaseViewUtil.BaseAppCompatActivity<ActivityNoticeBinding>(R.layout.activity_notice) {
    private lateinit var noticeAdapter: NoticeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
        setNoticeListAdapter(
            mutableListOf(
                NoticeResponseData(1, "스푼피드에 오신 것을 환영합니다!", "공지글 입니다",  "2022.02-0222.02")
            )
        )
        setBackBtnClickListener()
    }


    private fun setNoticeListAdapter(dataList: MutableList<NoticeResponseData>) {
        noticeAdapter = NoticeAdapter(dataList) {
            Intent(this, NoticeDetailActivity::class.java).apply {
                startActivity(this)
            }
        }
        with(binding) {
            rvNotice.adapter = noticeAdapter
            rvNotice.layoutManager = LinearLayoutManager(this@NoticeActivity)
        }
    }

    private fun setBackBtnClickListener() {
        binding.ivNoticeBack.setOnClickListener {
            finish()
        }
    }
}
package com.fork.spoonfeed.presentation.ui.mypage.view

import android.os.Bundle
import android.text.Editable
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.ActivityMyPageMyInfoBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPageMyInfoActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivityMyPageMyInfoBinding>(R.layout.activity_my_page_my_info) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
        setOnClickListener()
    }

    private fun setOnClickListener() {
        binding.mtMypageMyInfoUpdateTitle.setNavigationOnClickListener {
            finish()
        }
        binding.ivMypageMyInfoUpdateNameClear.setOnClickListener {
            binding.etMypageMyInfoUpdateName.setText("")
        }
        binding.mbMypageMyInfoUpdate.setOnClickListener {
            finish()
        }
    }
}

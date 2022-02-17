package com.fork.spoonfeed.presentation.ui.mypage.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.ActivityMyPostManagementBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil

class MyPostManagementActivity : BaseViewUtil.BaseAppCompatActivity<ActivityMyPostManagementBinding>(R.layout.activity_my_post_management) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
    }
}
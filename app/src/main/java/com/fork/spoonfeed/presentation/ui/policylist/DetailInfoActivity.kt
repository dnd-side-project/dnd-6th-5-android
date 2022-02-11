package com.fork.spoonfeed.presentation.ui.policylist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.ActivityDetailInfoBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailInfoActivity : BaseViewUtil.BaseAppCompatActivity<ActivityDetailInfoBinding>(R.layout.activity_detail_info) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
    }
}
package com.fork.spoonfeed.presentation.ui.community.view

import android.content.Intent
import android.os.Bundle
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.ActivityCommunityPostCreateBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil

class CommunityPostCreateActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivityCommunityPostCreateBinding>(R.layout.activity_community_post_create) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
        binding.mbCommunityPostCreate.setOnClickListener {
            startActivity(Intent(baseContext, CommunityPostActivity::class.java))
            finish()
        }
    }
}

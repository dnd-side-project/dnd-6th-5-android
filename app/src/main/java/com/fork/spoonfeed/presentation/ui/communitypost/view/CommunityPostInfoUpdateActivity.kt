package com.fork.spoonfeed.presentation.ui.communitypost.view

import android.os.Bundle
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.ActivityCommunityPostInfoUpdateBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil

class CommunityPostInfoUpdateActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivityCommunityPostInfoUpdateBinding>(R.layout.activity_community_post_info_update) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
        setOnClickListener()
    }

    private fun setOnClickListener() {
        binding.mtCommunityPostInfoUpdateTitle.setNavigationOnClickListener {
            finish()
        }
        binding.mbCommunityPostInfoUpdate.setOnClickListener {
            finish()
        }
    }
}

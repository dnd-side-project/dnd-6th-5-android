package com.fork.spoonfeed.presentation.ui.communitypost.view

import android.os.Bundle
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.ActivityCommunityPostBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil

class CommunityPostActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivityCommunityPostBinding>(R.layout.activity_community_post) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {}
}

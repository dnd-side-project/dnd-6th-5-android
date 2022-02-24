package com.fork.spoonfeed.presentation.ui.mypage.view

import android.os.Bundle
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.ActivityMyPostManagementBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.ui.community.adapter.TabLayoutAdapter
import com.fork.spoonfeed.presentation.util.setBackBtnClickListener
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPostManagementActivity : BaseViewUtil.BaseAppCompatActivity<ActivityMyPostManagementBinding>(R.layout.activity_my_post_management) {
    private lateinit var myPostManagementAdapter: TabLayoutAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
        initTabLayoutAdapter()
        initTabLayout()
        this.setBackBtnClickListener(binding.ivMypostmanagementBack)
    }

    private fun initTabLayoutAdapter() {
        val fragmentList = listOf(MyPostManagementPostFragment(), MyPostManagementCommentFragment())
        myPostManagementAdapter = TabLayoutAdapter(this)
        myPostManagementAdapter.fragments.addAll(fragmentList)
        binding.vpMypostmanagement.adapter = myPostManagementAdapter
    }

    private fun initTabLayout() {
        val tabLable = listOf("게시글", "댓글")
        TabLayoutMediator(binding.tlMypostmanagement, binding.vpMypostmanagement) { tab, position ->
            tab.text = tabLable[position]
        }.attach()
    }
}
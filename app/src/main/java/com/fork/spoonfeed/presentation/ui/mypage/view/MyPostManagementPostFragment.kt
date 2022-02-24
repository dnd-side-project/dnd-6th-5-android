package com.fork.spoonfeed.presentation.ui.mypage.view

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.FragmentMyPostManagementBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.ui.community.adapter.PostAdapter

class MyPostManagementPostFragment : BaseViewUtil.BaseFragment<FragmentMyPostManagementBinding>(R.layout.fragment_my_post_management) {
    private lateinit var myPostManagementPostAdapter: PostAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun initView() {
        initRvAdapter()
    }


    private fun initRvAdapter() {
        myPostManagementPostAdapter = PostAdapter(true) {
            //커뮤니티 상세 페이지로 이동
        }
        with(binding) {
            rvMypostmanagement.adapter = myPostManagementPostAdapter
            rvMypostmanagement.layoutManager = LinearLayoutManager(requireContext())
        }
    }
}

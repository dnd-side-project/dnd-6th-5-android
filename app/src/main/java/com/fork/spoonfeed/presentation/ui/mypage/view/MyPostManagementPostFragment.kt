package com.fork.spoonfeed.presentation.ui.mypage.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.FragmentMyPostManagementBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.ui.community.view.CommunityFragment
import com.fork.spoonfeed.presentation.ui.communitypost.view.CommunityPostActivity
import com.fork.spoonfeed.presentation.ui.communitypost.view.CommunityPostCreateActivity
import com.fork.spoonfeed.presentation.ui.mypage.adapter.MyPostAdapter
import com.fork.spoonfeed.presentation.ui.mypage.viewmodel.MyPageViewModel
import com.fork.spoonfeed.presentation.ui.onboarding.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPostManagementPostFragment : BaseViewUtil.BaseFragment<FragmentMyPostManagementBinding>(R.layout.fragment_my_post_management) {
    private lateinit var myPostManagementPostAdapter: MyPostAdapter
    private val myPageViewModel: MyPageViewModel by viewModels()
    private var initPostNull = false
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.myPageViewModel = myPageViewModel
        initView()
    }

    override fun onResume() {
        super.onResume()
        initData()
    }

    override fun initView() {
        initData()
        setMyPolicyListObserve()
        initRvAdapter()
        setMyPolicyListEmptyObserve()
    }

    fun setMyPolicyListEmptyObserve() {
        myPageViewModel.isMyPostEmpty.observe(this) { isMyPostEmpty ->
            if (isMyPostEmpty) {
                binding.rvMypostmanagement.visibility = View.GONE
                binding.ctlMypostmanagementNoPost.visibility = View.VISIBLE
            } else {
                binding.ctlMypostmanagementNoPost.visibility = View.GONE
                binding.rvMypostmanagement.visibility = View.VISIBLE
            }
        }
    }

    private fun setMyPolicyListObserve() {
        myPageViewModel.myPostList.observe(this) { myPostList ->
            myPostManagementPostAdapter.submitList(myPostList)
        }

        myPageViewModel.deletePostSuccess.observe(this) { deleteSuccess ->
            if (deleteSuccess)
                initData()
        }
    }

    private fun initData() {
        myPageViewModel.getMyPost()
        myPageViewModel.getNickName()
    }

    private fun initRvAdapter() {
        myPostManagementPostAdapter = MyPostAdapter(myPageViewModel, requireActivity()) {
            startActivity(Intent(requireContext(), CommunityPostActivity::class.java).apply {
                putExtra(CommunityFragment.POST_PK, it.postId)
            })
        }
        with(binding) {
            rvMypostmanagement.adapter = myPostManagementPostAdapter
            rvMypostmanagement.layoutManager = LinearLayoutManager(requireContext())
        }
    }
}

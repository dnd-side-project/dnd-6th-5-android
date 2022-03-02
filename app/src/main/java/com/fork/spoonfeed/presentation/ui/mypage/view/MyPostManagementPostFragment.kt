package com.fork.spoonfeed.presentation.ui.mypage.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.FragmentMyPostManagementBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.ui.communitypost.view.CommunityPostActivity
import com.fork.spoonfeed.presentation.ui.mypage.adapter.MyPostAdapter
import com.fork.spoonfeed.presentation.ui.mypage.viewmodel.MyPageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPostManagementPostFragment : BaseViewUtil.BaseFragment<FragmentMyPostManagementBinding>(R.layout.fragment_my_post_management) {
    private lateinit var myPostManagementPostAdapter: MyPostAdapter
    private val myPageViewModel: MyPageViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.myPageViewModel = myPageViewModel
        initView()
    }

    override fun initView() {
        myPageViewModel.getMyPost()
        setisMyPostEmptyObserve()
        setMyPolicyListObserve()
        initRvAdapter()
    }

    private fun setisMyPostEmptyObserve() {
        myPageViewModel.isMyPostEmpty.observe(this) { isMyPostEmpty ->
            isMyPostEmpty.let {
                if (isMyPostEmpty) {
                    binding.rvMypostmanagement.visibility = View.GONE
                    binding.ctlMypostmanagementNoPost.visibility = View.VISIBLE
                } else {
                    binding.ctlMypostmanagementNoPost.visibility = View.GONE
                    binding.rvMypostmanagement.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setMyPolicyListObserve() {
        myPageViewModel.myPostList.observe(this) { myPostList ->
            myPostManagementPostAdapter.submitList(myPostList)
        }
    }

    private fun initRvAdapter() {
        myPostManagementPostAdapter = MyPostAdapter(requireActivity()) {
            val intent = Intent(requireActivity(), CommunityPostActivity::class.java)
            startActivity(intent)
        }
        with(binding) {
            rvMypostmanagement.adapter = myPostManagementPostAdapter
            rvMypostmanagement.layoutManager = LinearLayoutManager(requireContext())
        }
    }
}

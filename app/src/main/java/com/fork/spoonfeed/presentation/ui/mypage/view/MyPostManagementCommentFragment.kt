package com.fork.spoonfeed.presentation.ui.mypage.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.FragmentMyPostManagementCommentBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.ui.communitypost.view.CommunityPostActivity
import com.fork.spoonfeed.presentation.ui.mypage.adapter.MyCommentAdapter
import com.fork.spoonfeed.presentation.ui.mypage.viewmodel.MyPageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPostManagementCommentFragment : BaseViewUtil.BaseFragment<FragmentMyPostManagementCommentBinding>(R.layout.fragment_my_post_management_comment) {
    private lateinit var myCommentAdapter: MyCommentAdapter
    private val myPageViewModel: MyPageViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.myPageViewModel = myPageViewModel
        initView()
    }

    override fun initView() {
        myPageViewModel.getMyComment()
        setisMyCommentEmptyObserve()
        setMyCommentListObserve()
        initRvAdapter()

    }

    private fun setisMyCommentEmptyObserve() {
        myPageViewModel.isMyCommentEmpty.observe(this) { isMyCommentEmpty ->
            isMyCommentEmpty.let {
                if (isMyCommentEmpty) {
                    binding.rvMypostmanagement.visibility = View.GONE
                    binding.ctlMypostmanagementNoComment.visibility = View.VISIBLE
                } else {
                    binding.ctlMypostmanagementNoComment.visibility = View.GONE
                    binding.rvMypostmanagement.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setMyCommentListObserve() {
        myPageViewModel.myCommentList.observe(this) { myCommentList ->
            myCommentAdapter.submitList(myCommentList)
        }
    }

    private fun initRvAdapter() {
        myCommentAdapter = MyCommentAdapter {
            val intent = Intent(requireActivity(), CommunityPostActivity::class.java)
            startActivity(intent)
        }
        with(binding) {
            rvMypostmanagement.adapter = myCommentAdapter
            rvMypostmanagement.layoutManager = LinearLayoutManager(requireContext())
        }
    }
}
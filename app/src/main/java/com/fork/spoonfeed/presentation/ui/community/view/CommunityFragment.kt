package com.fork.spoonfeed.presentation.ui.community.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.FragmentCommunityBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.ui.community.adapter.PostAdapter
import com.fork.spoonfeed.presentation.ui.community.viewmodel.CommunityViewModel
import com.fork.spoonfeed.presentation.ui.communitypost.view.CommunityPostActivity
import com.fork.spoonfeed.presentation.ui.communitypost.view.CommunityPostCreateActivity
import com.fork.spoonfeed.presentation.ui.mypage.viewmodel.MyPageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommunityFragment :
    BaseViewUtil.BaseFragment<FragmentCommunityBinding>(R.layout.fragment_community) {

    private val communityViewModel: CommunityViewModel by activityViewModels()
    private val myPageViewModel: MyPageViewModel by activityViewModels()
    private lateinit var communityAdapter: PostAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.communityViewModel = communityViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        initView()
    }

    override fun onResume() {
        super.onResume()
        initData()
    }

    override fun initView() {
        myPageViewModel.getNickName()
        setCommunityAdapter()
        setObserver()
        setFilterClickObserve()
        setInitClickListener()
    }

    private fun initData() {
        communityViewModel.getPostData()
        myPageViewModel.getNickName()
    }

    private fun setCommunityAdapter() {
        communityAdapter = PostAdapter {
            startActivity(Intent(requireContext(), CommunityPostActivity::class.java).apply {
                putExtra(POST_PK, it.id)
            })
        }
        with(binding) {
            rvCommunity.adapter = communityAdapter
            rvCommunity.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun setObserver() {
        communityViewModel.filteredPostData.observe(viewLifecycleOwner) {
            communityAdapter.submitList(it)
        }
        communityViewModel.selectedFilter.observe(viewLifecycleOwner) {
            communityViewModel.getPostData()
        }

        myPageViewModel.deletePostSuccess.observe(viewLifecycleOwner) { deleteSuccess ->
            if (deleteSuccess)
                communityViewModel.getPostData()
        }
    }

    private fun setInitClickListener() {
        binding.ivCommunityMagnifyGlass.setOnClickListener { SearchInputActivity.start(requireContext()) }
        binding.ivCommunityFloatingButton.setOnClickListener { CommunityPostCreateActivity.start(requireContext()) }
    }

    private fun setFilterClickObserve() {
        communityViewModel.isFilterClicked.observe(viewLifecycleOwner) { isFilterClicked ->
            isFilterClicked.let {
                if (isFilterClicked) {
                    showBottomFilterDialog()
                }
            }
        }
    }

    private fun showBottomFilterDialog() {
        val bottomSheetFragment = BottomDialogCommunityFragment()
        bottomSheetFragment.show(
            requireActivity().supportFragmentManager,
            bottomSheetFragment.tag
        )
        communityViewModel.filterOnClickFalse()
    }

    companion object {
        const val POST_PK = "com.fork.spoonfeed.presentation.ui.community.view"
    }
}

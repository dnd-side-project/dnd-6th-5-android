package com.fork.spoonfeed.presentation.ui.community.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.fork.spoonfeed.R
import com.fork.spoonfeed.data.remote.model.community.ResponsePostAllData
import com.fork.spoonfeed.databinding.FragmentSearchInputResultBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.base.BaseViewUtil.BaseCategoryBottomDialogFragment.Companion.ALL
import com.fork.spoonfeed.presentation.ui.community.adapter.PostAdapter
import com.fork.spoonfeed.presentation.ui.community.viewmodel.SearchViewModel
import com.fork.spoonfeed.presentation.ui.communitypost.view.CommunityPostActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchInputResultFragment :
    BaseViewUtil.BaseFragment<FragmentSearchInputResultBinding>(R.layout.fragment_search_input_result) {

    private lateinit var category: String
    private lateinit var communityPostAdapter: PostAdapter
    private val viewModel: SearchViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun initView() {
        initData()
        initRvAdapter()
        setObserver()
    }

    private fun initData() {
        category = arguments?.get(SearchInputActivity.POST_CATEGORY) as String
    }

    private fun setObserver() {
        viewModel.searchPostAllData.observe(viewLifecycleOwner) {
            val newData = it.filter { searchData ->
                if (category == ALL) {
                    true
                } else {
                    category == searchData.category
                }
            }
            binding.ctlSearchInputResultNoPost.isVisible = newData.isEmpty()
            communityPostAdapter.submitList(newData)
        }
    }


    private fun initRvAdapter() {
        communityPostAdapter = PostAdapter(true) {
            startActivity(Intent(requireContext(), CommunityPostActivity::class.java).apply {
                putExtra(CommunityFragment.POST_PK, it.id)
            })
        }
        with(binding) {
            rvSearchInputResult.adapter = communityPostAdapter
            rvSearchInputResult.layoutManager = LinearLayoutManager(requireContext())
        }
    }
}

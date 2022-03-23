package com.fork.spoonfeed.presentation.ui.community.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.ActivitySearchInputBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.base.BaseViewUtil.BaseCategoryBottomDialogFragment.Companion.ALL
import com.fork.spoonfeed.presentation.base.BaseViewUtil.BaseCategoryBottomDialogFragment.Companion.DWELLING
import com.fork.spoonfeed.presentation.base.BaseViewUtil.BaseCategoryBottomDialogFragment.Companion.FINANCE
import com.fork.spoonfeed.presentation.ui.community.adapter.TabLayoutAdapter
import com.fork.spoonfeed.presentation.ui.community.viewmodel.SearchViewModel
import com.fork.spoonfeed.presentation.util.setBackBtnClickListener
import com.fork.spoonfeed.presentation.util.setTextColor
import com.fork.spoonfeed.presentation.util.setTextSize
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchInputActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivitySearchInputBinding>(R.layout.activity_search_input) {

    private lateinit var searchInputAdapter: TabLayoutAdapter
    private val viewModel: SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
        initTabLayoutAdapter()
        initTabLayout()
        setObserver()
        setInputField()
        this.setBackBtnClickListener(binding.ivSearchInputBack)
    }

    private fun initTabLayoutAdapter() {
        val fragmentList = listOf(
            SearchInputResultFragment().apply {
                arguments = bundleOf(POST_CATEGORY to ALL)
            },
            SearchInputResultFragment().apply {
                arguments = bundleOf(POST_CATEGORY to DWELLING)
            },
            SearchInputResultFragment().apply {
                arguments = bundleOf(POST_CATEGORY to FINANCE)
            }
        )
        searchInputAdapter = TabLayoutAdapter(this)
        searchInputAdapter.fragments.addAll(fragmentList)
        binding.vpSearchInput.adapter = searchInputAdapter
    }

    private fun initTabLayout() {
        val tabLabel = listOf("전체", "주거", "금융")
        TabLayoutMediator(binding.tlSearchInput, binding.vpSearchInput) { tab, position ->
            tab.text = tabLabel[position]
        }.attach()
        binding.tlSearchInput.isVisible = false
    }

    private fun setObserver() {
        viewModel.searchQuery.observe(this) {
            if (!binding.tlSearchInput.isVisible) {
                binding.tlSearchInput.isVisible = true
            }
            viewModel.searchPost()
        }
    }

    fun setInputField() {
        binding.etSearchInputBar.setTextSize(14f)
        binding.etSearchInputBar.setTextColor(this)

        binding.etSearchInputBar.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(newText: String?): Boolean {
                viewModel.updateSearchQuery(newText.toString())
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    companion object {
        const val POST_CATEGORY = "com.fork.spoonfeed.presentation.ui.community.view POST_CATEGORY"
    }
}

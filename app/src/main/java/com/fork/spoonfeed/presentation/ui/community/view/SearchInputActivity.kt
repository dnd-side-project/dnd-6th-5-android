package com.fork.spoonfeed.presentation.ui.community.view

import android.os.Bundle
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.ActivitySearchInputBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.ui.community.adapter.SearchInputAdapter
import com.google.android.material.tabs.TabLayoutMediator

class SearchInputActivity : BaseViewUtil.BaseAppCompatActivity<ActivitySearchInputBinding>(R.layout.activity_search_input) {
    private lateinit var searchInputAdapter: SearchInputAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
        initTabLayoutAdapter()
        initTabLayout()
        setBackBtnClickListener()
    }

    private fun initTabLayoutAdapter() {
        val fragmentList = listOf(SearchInputResultFragment(),SearchInputResultFragment(),SearchInputResultFragment())
        searchInputAdapter = SearchInputAdapter(this)
        searchInputAdapter.fragments.addAll(fragmentList)
        binding.vpSearchInput.adapter = searchInputAdapter
    }

    private fun initTabLayout() {
        val tabLable = listOf("전체", "주거", "금융")
        TabLayoutMediator(binding.tlSearchInput, binding.vpSearchInput) { tab, position ->
            tab.text = tabLable[position]
        }.attach()
    }

    private fun setBackBtnClickListener() {
        binding.ivSearchInputBack.setOnClickListener {
            finish()
        }
    }
}
package com.fork.spoonfeed.presentation.ui.community.view

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.FragmentSearchInputResultBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.ui.community.adapter.PostAdapter

class SearchInputResultFragment : BaseViewUtil.BaseFragment<FragmentSearchInputResultBinding>(R.layout.fragment_search_input_result) {
    private lateinit var communityPostAdapter: PostAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun initView() {
        initRvAdapter()
    }


    private fun initRvAdapter() {
        communityPostAdapter = PostAdapter(true) {
            /*      Intent(requireContext(), DetailInfoActivity::class.java).apply {
                      startActivity(this)
                  }*/
        }
        with(binding) {
            rvSearchInputResult.adapter = communityPostAdapter
            rvSearchInputResult.layoutManager = LinearLayoutManager(requireContext())
        }
    }
}

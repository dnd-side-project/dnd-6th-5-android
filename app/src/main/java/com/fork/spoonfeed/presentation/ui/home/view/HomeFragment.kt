package com.fork.spoonfeed.presentation.ui.home.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.FragmentHomeBinding
import com.fork.spoonfeed.presentation.MainActivity
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.base.BaseViewUtil.BaseCategoryBottomDialogFragment.Companion.ALL
import com.fork.spoonfeed.presentation.base.BaseViewUtil.BaseCategoryBottomDialogFragment.Companion.DWELLING
import com.fork.spoonfeed.presentation.base.BaseViewUtil.BaseCategoryBottomDialogFragment.Companion.FINANCE
import com.fork.spoonfeed.presentation.ui.home.adapter.MyLikePolicyAdapter
import com.fork.spoonfeed.presentation.ui.home.viewmodel.HomeViewModel
import com.fork.spoonfeed.presentation.ui.mypage.view.InterestedPolicyActivity
import com.fork.spoonfeed.presentation.ui.policylist.view.DetailInfoActivity
import com.fork.spoonfeed.presentation.ui.policylist.view.PolicyListActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseViewUtil.BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private lateinit var myLikePolicyAdapter: MyLikePolicyAdapter
    private val homeViewModel: HomeViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onResume() {
        super.onResume()
        initData()
    }

    override fun initView() {
        initClick()
        setMyLikePolicyListAdapter()
        setMyLikePolicyListObserve()
        initData()
    }

    private fun initData() {
        homeViewModel.getMyLikePolicy()
    }

    private fun initClick() {
        with(binding) {
            ivHomeAllBackground.setOnClickListener { PolicyListActivity.start(requireContext(), ALL) }
            ivHomeDwellingBackground.setOnClickListener { PolicyListActivity.start(requireContext(), DWELLING) }
            ivHomeFinanceBackground.setOnClickListener { PolicyListActivity.start(requireContext(), FINANCE) }
            ivHomeInterastedPolicyMore.setOnClickListener { InterestedPolicyActivity.start(requireContext()) }
            ctlHomeCustomizedPolicy.setOnClickListener { (activity as MainActivity).moveToPolicy() }
        }
    }

    private fun setMyLikePolicyListAdapter() {
        myLikePolicyAdapter = MyLikePolicyAdapter { DetailInfoActivity.start(requireContext(), it.policyId) }

        with(binding.rvHomeInterastedPolicyList) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = myLikePolicyAdapter
        }
    }

    private fun setMyLikePolicyListObserve() {
        homeViewModel.myLikePolicyList.observe(viewLifecycleOwner) { myLikePolicyList ->
            myLikePolicyAdapter.submitList(myLikePolicyList)
        }
    }
}
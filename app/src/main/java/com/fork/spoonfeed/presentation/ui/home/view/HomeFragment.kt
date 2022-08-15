package com.fork.spoonfeed.presentation.ui.home.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
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
import com.fork.spoonfeed.presentation.ui.policylist.view.PolicyListActivity.Companion.CATEGORY
import com.fork.spoonfeed.presentation.util.LinearLayoutManagerWrapper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseViewUtil.BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private lateinit var myLikePolicyAdapter: MyLikePolicyAdapter
    private val homeViewModel: HomeViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        LifeCycleEventLogger(javaClass.name).registerLogger(viewLifecycleOwner.lifecycle)
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
        setMyPolicyListEmptyObserve()
        initData()
    }

    private fun initData() {
        homeViewModel.getMyLikePolicy()
    }

    private fun initClick() {
        with(binding) {

            ivHomeAllBackground.setOnClickListener {
                val intent = Intent(requireContext(), PolicyListActivity::class.java).let {
                    it.putExtra(CATEGORY, ALL)
                }
                startActivity(intent)
            }
            ivHomeDwellingBackground.setOnClickListener {
                val intent = Intent(requireContext(), PolicyListActivity::class.java).let {
                    it.putExtra(CATEGORY, DWELLING)
                }
                startActivity(intent)
            }
            ivHomeFinanceBackground.setOnClickListener {
                val intent = Intent(requireContext(), PolicyListActivity::class.java).let {
                    it.putExtra(CATEGORY, FINANCE)
                }
                startActivity(intent)
            }
            ivHomeInterastedPolicyMore.setOnClickListener {
                val intent = Intent(requireContext(), InterestedPolicyActivity::class.java)
                startActivity(intent)
            }

            ctlHomeCustomizedPolicy.setOnClickListener {
                (activity as MainActivity).moveToPolicy()
            }
        }
    }

    private fun setMyLikePolicyListAdapter() {
        val linearLayoutManagerWrapepr = LinearLayoutManagerWrapper(requireContext(), LinearLayoutManager.VERTICAL, false)

        myLikePolicyAdapter = MyLikePolicyAdapter {
            Intent(requireContext(), DetailInfoActivity::class.java).apply {
                putExtra(DetailInfoActivity.POST_PK, it.policyId)
                startActivity(this)
            }
        }

        with(binding) {
            rvHomeInterastedPolicyList.adapter = myLikePolicyAdapter
            rvHomeInterastedPolicyList.layoutManager = linearLayoutManagerWrapepr
        }
    }

    private fun setMyLikePolicyListObserve() {
        homeViewModel.myLikePolicyList.observe(this) { myLikePolicyList ->
            myLikePolicyAdapter.submitList(myLikePolicyList)
        }
    }

    fun setMyPolicyListEmptyObserve() {
        homeViewModel.isMyLikePolicyListEmpty.observe(this) { isMyLikePolicyListEmpty ->
            if (isMyLikePolicyListEmpty) {
                binding.rvHomeInterastedPolicyList.visibility = View.GONE
                binding.ctlHomeNoInterastedPolicyList.visibility = View.VISIBLE
            } else {
                binding.ctlHomeNoInterastedPolicyList.visibility = View.GONE
                binding.rvHomeInterastedPolicyList.visibility = View.VISIBLE
            }
        }
    }
}
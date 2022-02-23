package com.fork.spoonfeed.presentation.ui.community.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.FragmentCommunityBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.ui.community.adapter.PostAdapter
import com.fork.spoonfeed.presentation.ui.community.adapter.PostResponseData
import com.fork.spoonfeed.presentation.ui.community.viewmodel.CommunityViewModel
import com.fork.spoonfeed.presentation.ui.communitypost.view.CommunityPostCreateActivity
import com.fork.spoonfeed.presentation.ui.policylist.view.BottomDialogFilterFragment
import com.fork.spoonfeed.presentation.ui.policylist.viewmodel.PolicyListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommunityFragment : BaseViewUtil.BaseFragment<FragmentCommunityBinding>(R.layout.fragment_community) {
    private val communityViewModel: CommunityViewModel by viewModels()
    private val policyListViewModel: PolicyListViewModel by activityViewModels()
    private lateinit var communityAdapter: PostAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.policyListViewModel = policyListViewModel
        binding.communityViewModel = communityViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.lifecycleOwner = viewLifecycleOwner
        initView()
    }

    override fun initView() {
        setCommunityAdapter(
            mutableListOf(
                PostResponseData(1, "주거", "청년 우대 통장", "김별명", "이 헌법시행 당시의 법력과 조항에 따라 그 효력을 지속한다 기타 조직,직무범위 기타 필요한 사항", "2022.02-0222.02", 7),
                PostResponseData(1, "금융", "청년 우대 통장", "김별명", "이 헌법시행 당시의 법력과 조항에 따라 그 효력을 지속한다 기타 조직,직무범위 기타 필요한 사항이라고생각하며 디앤디 5조입니다 디앤지 화이팅입니다!", "2022.02-0222.02", 7),
                PostResponseData(1, "주거", "청년 우대 통장", "김별명", "이 헌법시행 당시의 법력과 조항에 따라 그 효력을 지속한다 기타 조직,직무범위 기타 필요한 사항", "2022.02-0222.02", 7),
                PostResponseData(1, "주거", "청년 우대 통장", "김별명", "이 헌법시행 당시의 법력과 조항에 따라 그 효력을 지속한다 기타 조직,직무범위 기타 필요한 사항", "2022.02-0222.02", 7),
                PostResponseData(1, "금융", "청년 우대 통장", "김별명", "이 헌법시행 당시의 법력과 조항에 따라 그 효력을 지속한다 기타 조직,직무범위 기타 필요한 사항", "2022.02-0222.02", 7),
                PostResponseData(1, "주거", "청년 우대 통장", "김별명", "이 헌법시행 당시의 법력과 조항에 따라 그 효력을 지속한다 기타 조직,직무범위 기타 필요한 사항", "2022.02-0222.02", 7),
                PostResponseData(1, "주거", "청년 우대 통장", "김별명", "이 헌법시행 당시의 법력과 조항에 따라 그 효력을 지속한다 기타 조직,직무범위 기타 필요한 사항", "2022.02-0222.02", 7)
            )
        )
        setFilterClickObserve()
        setSearchClickListener()
        setFloatingClickListener()
    }

    private fun setCommunityAdapter(dataList: MutableList<PostResponseData>) {
        communityAdapter = PostAdapter(false, dataList) {
            /*      Intent(requireContext(), DetailInfoActivity::class.java).apply {
                      startActivity(this)
                  }*/
        }
        with(binding) {
            rvCommunity.adapter = communityAdapter
            rvCommunity.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun setSearchClickListener() {
        binding.ivCommunityMagnifyGlass.setOnClickListener {
            val intent = Intent(requireContext(), SearchInputActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setFloatingClickListener() {
        binding.ivCommunityFloatingButton.setOnClickListener {
            val intent = Intent(context, CommunityPostCreateActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setFilterClickObserve() {
        policyListViewModel.isFilterClicked.observe(this) { isFilterClicked ->
            isFilterClicked.let {
                if (isFilterClicked) {
                    showBottomFilterDialog()
                }
            }
        }
    }

    private fun showBottomFilterDialog() {
        val bottomSheetFragment = BottomDialogFilterFragment()
        bottomSheetFragment.show(
            requireActivity().supportFragmentManager,
            bottomSheetFragment.tag
        )
        policyListViewModel.filterOnClickFalse()
    }
}

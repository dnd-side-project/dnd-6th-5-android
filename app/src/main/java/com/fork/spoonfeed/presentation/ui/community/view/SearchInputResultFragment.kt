package com.fork.spoonfeed.presentation.ui.community.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.FragmentMyPageBinding
import com.fork.spoonfeed.databinding.FragmentSearchInputResultBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.ui.community.adapter.CommunityAdapter
import com.fork.spoonfeed.presentation.ui.community.adapter.CommunityResponseData
import dagger.hilt.android.AndroidEntryPoint


class SearchInputResultFragment : BaseViewUtil.BaseFragment<FragmentSearchInputResultBinding>(R.layout.fragment_search_input_result) {
    private lateinit var communityAdapter: CommunityAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun initView() {
        initRvAdapter(
            mutableListOf(
                CommunityResponseData(1, "주거", "청년 우대 통장", "김별명", "이 헌법시행 당시의 법력과 조항에 따라 그 효력을 지속한다 기타 조직,직무범위 기타 필요한 사항", "2022.02-0222.02", 7),
                CommunityResponseData(1, "금융", "청년 우대 통장", "김별명", "이 헌법시행 당시의 법력과 조항에 따라 그 효력을 지속한다 기타 조직,직무범위 기타 필요한 사항이라고생각하며 디앤디 5조입니다 디앤지 화이팅입니다!", "2022.02-0222.02", 7),
                CommunityResponseData(1, "주거", "청년 우대 통장", "김별명", "이 헌법시행 당시의 법력과 조항에 따라 그 효력을 지속한다 기타 조직,직무범위 기타 필요한 사항", "2022.02-0222.02", 7),
                CommunityResponseData(1, "주거", "청년 우대 통장", "김별명", "이 헌법시행 당시의 법력과 조항에 따라 그 효력을 지속한다 기타 조직,직무범위 기타 필요한 사항", "2022.02-0222.02", 7),
                CommunityResponseData(1, "금융", "청년 우대 통장", "김별명", "이 헌법시행 당시의 법력과 조항에 따라 그 효력을 지속한다 기타 조직,직무범위 기타 필요한 사항", "2022.02-0222.02", 7),
                CommunityResponseData(1, "주거", "청년 우대 통장", "김별명", "이 헌법시행 당시의 법력과 조항에 따라 그 효력을 지속한다 기타 조직,직무범위 기타 필요한 사항", "2022.02-0222.02", 7),
                CommunityResponseData(1, "주거", "청년 우대 통장", "김별명", "이 헌법시행 당시의 법력과 조항에 따라 그 효력을 지속한다 기타 조직,직무범위 기타 필요한 사항", "2022.02-0222.02", 7)
            )
        )
    }
    

    private fun initRvAdapter(dataList: MutableList<CommunityResponseData>) {
        communityAdapter = CommunityAdapter(dataList) {
            /*      Intent(requireContext(), DetailInfoActivity::class.java).apply {
                      startActivity(this)
                  }*/
        }
        with(binding) {
            rvSearchInputResult.adapter = communityAdapter
            rvSearchInputResult.layoutManager = LinearLayoutManager(requireContext())
        }
    }
}
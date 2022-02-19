package com.fork.spoonfeed.presentation.ui.community.view

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.FragmentSearchInputResultBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.ui.community.adapter.PostAdapter
import com.fork.spoonfeed.presentation.ui.community.adapter.PostResponseData


class SearchInputResultFragment : BaseViewUtil.BaseFragment<FragmentSearchInputResultBinding>(R.layout.fragment_search_input_result) {
    private lateinit var communityPostAdapter: PostAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun initView() {
        initRvAdapter(
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
    }


    private fun initRvAdapter(dataList: MutableList<PostResponseData>) {
        communityPostAdapter = PostAdapter(true, dataList) {
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
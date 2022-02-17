package com.fork.spoonfeed.presentation.ui.mypage.view

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.FragmentMyPostManagementCommentBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.ui.mypage.adapter.MyCommentAdapter
import com.fork.spoonfeed.presentation.ui.mypage.adapter.MyCommentResponseData

class MyPostManagementCommentFragment : BaseViewUtil.BaseFragment<FragmentMyPostManagementCommentBinding>(R.layout.fragment_my_post_management_comment) {
    private lateinit var myCommentAdapter: MyCommentAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun initView() {
        initRvAdapter(
            mutableListOf(
                MyCommentResponseData(1, "후기 남깁니다 너무 좋았던 정책입니다. 일단 저는 직장인이고요", "[내일채움공제 후기]", "2022.02-0222.02"),
                MyCommentResponseData(1, "스푼피드 덕분에 좋은 정책을 아고가네요", "[내일채움공제 후기]", "2022.02-0222.02"),
                MyCommentResponseData(1, "이런 정책이 있다는 걸 몰랐는데! 후기 잘 보았습니다~~", "[내일채움공제 후기]", "2022.02-0222.02"),
                MyCommentResponseData(1, "후기 남깁니다 너무 좋았던 정책입니다. 일단 저는 직장인이고요", "[내일채움공제 후기]", "2022.02-0222.02"),
                MyCommentResponseData(1, "덕분에 좋은 정책을 알고가네요", "[내일채움공제 후기]", "2022.02-0222.02"),
                MyCommentResponseData(1, "이런 정책이 있다는 걸 몰랐는데! 후기 잘 보았습니다~~", "[내일채움공제 후기]", "2022.02-0222.02"),
                MyCommentResponseData(1, "후기 남깁니다 너무 좋았던 정책입니다. 일단 저는 직장인이고요", "[내일채움공제 후기]", "2022.02-0222.02"),
                MyCommentResponseData(1, "후기 남깁니다 너무 좋았던 정책입니다. 일단 저는 직장인이고요", "[내일채움공제 후기]", "2022.02-0222.02"),
                MyCommentResponseData(1, "스푼피드 덕분에 좋은 정책을 알고가네요", "[내일채움공제 후기]", "2022.02-0222.02"),
                MyCommentResponseData(1, "이런 정책이 있다는 걸 몰랐는데! 후기 잘 보았습니다~~", "[내일채움공제 후기]", "2022.02-0222.02")
            )
        )
    }

    private fun initRvAdapter(dataList: MutableList<MyCommentResponseData>) {
        myCommentAdapter = MyCommentAdapter(dataList) {
                 //댓글 작성했던 커뮤니티 상세 페이지로 이동
        }
        with(binding) {
            rvMypostmanagement.adapter = myCommentAdapter
            rvMypostmanagement.layoutManager = LinearLayoutManager(requireContext())
        }
    }
}
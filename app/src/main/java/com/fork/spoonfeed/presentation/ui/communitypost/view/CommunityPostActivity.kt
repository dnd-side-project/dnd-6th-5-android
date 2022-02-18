package com.fork.spoonfeed.presentation.ui.communitypost.view

import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.ActivityCommunityPostBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.ui.communitypost.adapter.CommentAdapter
import com.fork.spoonfeed.presentation.ui.communitypost.adapter.CommentResponseData

class CommunityPostActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivityCommunityPostBinding>(R.layout.activity_community_post) {

    private lateinit var commentAdapter: CommentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
        setCommentAdapter()

    }

    private fun setCommentAdapter() {
        commentAdapter = CommentAdapter()
        binding.rvCommunityPostComment.adapter = commentAdapter
        binding.rvCommunityPostComment.addItemDecoration(ItemDecoration())

        commentAdapter.submitList(
            mutableListOf(
                CommentResponseData(1, "김별명", "2021/01/21", "댓글 데이터"),
                CommentResponseData(1, "김별명", "2021/01/21", "댓글 데이터"),
                CommentResponseData(1, "김별명", "2021/01/21", "댓글 데이터"),
                CommentResponseData(1, "김별명", "2021/01/21", "댓글 데이터"),
                CommentResponseData(1, "김별명", "2021/01/21", "댓글 데이터"),
            )
        )
    }
}

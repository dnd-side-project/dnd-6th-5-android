package com.fork.spoonfeed.presentation.ui.communitypost.view

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
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
        setClickListener()
    }

    private fun setCommentAdapter() {
        commentAdapter = CommentAdapter()
        binding.rvCommunityPostComment.adapter = commentAdapter
        binding.rvCommunityPostComment.addItemDecoration(ItemDecoration())

        // TODO ViewModel 데이터 바인딩
        commentAdapter.submitList(
            mutableListOf(
                CommentResponseData(1, "김별명", "2021/01/21", "댓글 데이터"),
                CommentResponseData(1, "김별명", "2021/01/21", "댓글 데이터"),
                CommentResponseData(1, "김별명", "2021/01/21", "댓글 데이터"),
                CommentResponseData(1, "김별명", "2021/01/21", "댓글 데이터"),
                CommentResponseData(1, "김별명", "2021/01/21", "댓글 데이터"),
            )
        )
        binding.tvCommunityPostCommentCount.text = commentAdapter.itemCount.toString()
    }

    private fun setClickListener() {
        binding.mtCommunityPostTitle.setNavigationOnClickListener {
            finish()
        }
        binding.ivCommunityPostDetail.setOnClickListener {
            if (binding.llCommunityPostDetail.isVisible) {
                binding.llCommunityPostDetail.visibility = View.GONE
                binding.ivCommunityPostDetail.setImageResource(R.drawable.ic_chevron_down)
            } else {
                binding.llCommunityPostDetail.visibility = View.VISIBLE
                binding.ivCommunityPostDetail.setImageResource(R.drawable.ic_chevron_up)
            }
        }
    }
}

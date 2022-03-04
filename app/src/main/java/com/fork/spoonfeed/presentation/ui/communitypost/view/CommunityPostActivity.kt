package com.fork.spoonfeed.presentation.ui.communitypost.view

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import android.widget.Button
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.fork.spoonfeed.R
import com.fork.spoonfeed.data.remote.model.policy.RequestFilteredPolicy
import com.fork.spoonfeed.databinding.ActivityCommunityPostBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.base.BaseViewUtil.BaseCategoryBottomDialogFragment.Companion.DWELLING
import com.fork.spoonfeed.presentation.ui.communitypost.adapter.CommentAdapter
import com.fork.spoonfeed.presentation.ui.communitypost.viewmodel.CommunityPostViewModel
import com.fork.spoonfeed.presentation.ui.mypage.view.MyPostManagementCommentFragment
import com.fork.spoonfeed.presentation.ui.policy.view.filter.PolicyFilterLevelThreeFragment
import com.fork.spoonfeed.presentation.util.showFloatingDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommunityPostActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivityCommunityPostBinding>(R.layout.activity_community_post) {

    private val communityPostViewModel: CommunityPostViewModel by viewModels()
    private lateinit var commentAdapter: CommentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
        binding.viewModel = communityPostViewModel
        binding.lifecycleOwner = this
        setCommentAdapter()
        setCommentEditor()
        setClickListener()
        setObserver()
        setInitLayout()
        initData()
    }

    private fun setCommentAdapter() {
        commentAdapter = CommentAdapter()
        binding.rvCommunityPostComment.adapter = commentAdapter
        binding.rvCommunityPostComment.addItemDecoration(ItemDecoration())
        binding.tvCommunityPostCommentCount.text = commentAdapter.itemCount.toString()
    }

    private fun setInitLayout() {

    }

    private fun setCommentEditor() {
        binding.etCommunityPostCommentInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.isNullOrEmpty()) {
                    communityPostViewModel.setCommentInput(null)
                } else {
                    communityPostViewModel.setCommentInput(s.toString())
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
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
        binding.ivCommunityPostEdit.setOnClickListener {

        }
        binding.ivCommunityPostCommentInput.setOnClickListener {
            communityPostViewModel.postComment()
        }
    }

    private fun setObserver() {
        communityPostViewModel.postDetailData.observe(this, {
            setCategoryBackground(it.category)
        })
        communityPostViewModel.postCommentData.observe(this, {
            commentAdapter.submitList(it)
        })
        communityPostViewModel.commentInput.observe(this, {
            binding.ivCommunityPostCommentInput.backgroundTintList = if (it != null) {
                ColorStateList.valueOf(ContextCompat.getColor(baseContext, R.color.gray06))
            } else {
                ColorStateList.valueOf(ContextCompat.getColor(baseContext, R.color.gray03))
            }
        })
        communityPostViewModel.isCommentPostSuccess.observe(this, {
            if (it) {
                binding.etCommunityPostCommentInput.setText("")
            }
            communityPostViewModel.initData()
        })
    }

    private fun setCategoryBackground(category: String) {
        binding.tvCommunityPostCategory.background = if (category == DWELLING) {
            ContextCompat.getDrawable(baseContext, R.drawable.bg_dwelling_blue_radius_4dp)
        } else {
            ContextCompat.getDrawable(baseContext, R.drawable.bg_finance_purple_radius_4dp)
        }
    }

    private fun initData() {
        communityPostViewModel.initData()
    }
}


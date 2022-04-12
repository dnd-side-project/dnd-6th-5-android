package com.fork.spoonfeed.presentation.ui.communitypost.view

import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.fork.spoonfeed.R
import com.fork.spoonfeed.data.remote.model.community.ResponsePostData
import com.fork.spoonfeed.data.remote.model.user.ResponseUserData
import com.fork.spoonfeed.databinding.ActivityCommunityPostBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.base.BaseViewUtil.BaseCategoryBottomDialogFragment.Companion.DWELLING
import com.fork.spoonfeed.presentation.ui.communitypost.adapter.CommentAdapter
import com.fork.spoonfeed.presentation.ui.communitypost.viewmodel.CommunityPostViewModel
import com.fork.spoonfeed.presentation.ui.mypage.view.BottomDialogMyPageFragment
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
        initUserData()
    }

    private fun initUserData() {
        communityPostViewModel.initUserData()
        communityPostViewModel.userData.observe(this) {
            setCommentAdapter(it)
            setCommentEditor()
            setClickListener()
            setObserver()
        }
    }

    override fun onResume() {
        super.onResume()
        initData()
    }

    private fun setCommentAdapter(userData: ResponseUserData.Data.User) {
        commentAdapter = CommentAdapter(supportFragmentManager, userData, ::commentUpdate, ::commentDelete)
        binding.rvCommunityPostComment.adapter = commentAdapter
        binding.rvCommunityPostComment.addItemDecoration(ItemDecoration())
        binding.tvCommunityPostCommentCount.text = commentAdapter.itemCount.toString()
    }

    private fun commentUpdate(comment: ResponsePostData.Data.Comment) {
        communityPostViewModel.updateComment(comment)
    }

    private fun commentDelete(comment: ResponsePostData.Data.Comment) {
        communityPostViewModel.deleteComment(comment)
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
        binding.ivCommunityPostCommentInput.setOnClickListener {
            communityPostViewModel.postComment()
            hideKeyboard()
        }
    }

    private fun setPostEditUserClickListener() {
        binding.ivCommunityPostEdit.setOnClickListener {
            showUserBottomDialog()
        }
    }

    private fun setPostEditWriterClickListener() {
        binding.ivCommunityPostEdit.setOnClickListener {
            showWriterBottomDialog()
        }
    }

    private fun setObserver() {
        communityPostViewModel.postDetailData.observe(this) {
            setCategoryBackground(it.category)
            if (it.author == communityPostViewModel.getUserData()?.nickname) setPostEditWriterClickListener()
            else setPostEditUserClickListener()
        }
        communityPostViewModel.postCommentData.observe(this) {
            commentAdapter.submitList(it)
        }
        communityPostViewModel.commentInput.observe(this) {
            binding.ivCommunityPostCommentInput.backgroundTintList = if (it != null) {
                ColorStateList.valueOf(ContextCompat.getColor(baseContext, R.color.gray06))
            } else {
                ColorStateList.valueOf(ContextCompat.getColor(baseContext, R.color.gray03))
            }
        }
        communityPostViewModel.isCommentPostSuccess.observe(this) {
            if (it) {
                binding.etCommunityPostCommentInput.setText("")
            }
            communityPostViewModel.initData()
        }
        communityPostViewModel.deletePostSuccess.observe(this) {
            if (it) {
                finish()
            }
        }
        communityPostViewModel.updateComment.observe(this) {
            binding.etCommunityPostCommentInput.setText(it.content)
            showKeyboard()
        }
        communityPostViewModel.deleteCommentSuccess.observe(this) {
            communityPostViewModel.initData()
        }
    }

    private fun showKeyboard() {
        binding.etCommunityPostCommentInput.requestFocus()
        (getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager).showSoftInput(
            binding.etCommunityPostCommentInput,
            0
        )
        binding.etCommunityPostCommentInput.setSelection(binding.etCommunityPostCommentInput.text.length)
    }

    private fun hideKeyboard() {
        (getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
            binding.etCommunityPostCommentInput.windowToken,
            0
        )
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

    private fun showWriterBottomDialog() {
        val bottomDialogMyPageFragment = BottomDialogMyPageFragment(communityPostViewModel.getPk()!!, BottomDialogMyPageFragment.MANAGEMENT_NO_COMMENT, BottomDialogMyPageFragment.COMMUNITY_POST)
        bottomDialogMyPageFragment.show(
            supportFragmentManager,
            bottomDialogMyPageFragment.tag
        )
    }

    private fun showUserBottomDialog() {
        val bottomDialogReportUser = BottomDialogReport(communityPostViewModel.getPk()!!)
        bottomDialogReportUser.show(
            supportFragmentManager,
            bottomDialogReportUser.tag
        )
    }
}


package com.fork.spoonfeed.presentation.ui.mypage.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.activityViewModels
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.FragmentBottomDialogMyPageBinding
import com.fork.spoonfeed.domain.model.CommentData
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.ui.community.view.CommunityFragment
import com.fork.spoonfeed.presentation.ui.communitypost.view.CommunityPostActivity
import com.fork.spoonfeed.presentation.ui.communitypost.view.CommunityPostCreateActivity
import com.fork.spoonfeed.presentation.ui.mypage.viewmodel.MyPageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BottomDialogMyPageFragment :
    BaseViewUtil.BaseCategoryBottomDialogFragment<FragmentBottomDialogMyPageBinding>(R.layout.fragment_bottom_dialog_my_page) {
    private val myPageViewModel: MyPageViewModel by activityViewModels()

    private lateinit var commentData: CommentData
    private lateinit var editType: String
    private lateinit var commentPk: String
    private var postPk = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun initView() {
        setEditType()
        setClickListener()
    }

    private fun setEditType() {
        postPk = arguments?.getInt(POST_PK, 4) ?: 0
        commentPk = arguments?.getString(COMMENT_PK) ?: ""
        editType = arguments?.getString(EDIT_TYPE) ?: ""

        if (arguments?.getSerializable(COMMENT_DATA) != null)
            commentData = arguments?.getSerializable(COMMENT_DATA) as CommentData
    }

    private fun setClickListener() {
        with(binding) {
            when (editType) {
                //게시글 edit
                POST -> {
                    tvBottomDialogMypageEdit.setOnClickListener {
                        startActivity(
                            Intent(context, CommunityPostCreateActivity::class.java)
                                .putExtra(CommunityPostCreateActivity.POST_ID, postPk)
                        )
                        dialog?.dismiss()
                    }
                    tvBottomDialogMypageDelete.setOnClickListener {
                        setHandler()
                        myPageViewModel.deleteMyPost(postPk)

                        if (activity is CommunityPostActivity)
                            (activity as CommunityPostActivity).finish()
                    }
                }

                //마이페이지에서 댓글 edit
                MYPAGE_COMMENT -> {
                    tvBottomDialogMypageEdit.setOnClickListener {
                        startActivity(
                            Intent(context, CommunityPostActivity::class.java)
                                .putExtra(CommunityFragment.POST_PK, postPk)
                        )
                        dialog?.dismiss()
                    }

                    tvBottomDialogMypageDelete.setOnClickListener {
                        myPageViewModel.deleteMyComment(postPk, commentPk)
                        setHandler()
                    }
                }

                //커뮤니티 글에서 댓글 edit
                COMMUNITY_COMMENT -> {
                    tvBottomDialogMypageEdit.setOnClickListener {
                        commentData.commentUpdateListener(commentData.data)
                        dialog?.dismiss()
                    }

                    tvBottomDialogMypageDelete.setOnClickListener {
                        commentData.commentDeleteListener(commentData.data)
                        setHandler()
                    }
                }
            }

            //그외 모든 닫기버튼 클릭시
            tvBottomDialogMypageClose.setOnClickListener {
                setHandler()
            }
        }
    }

    private fun setHandler() {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({ dialog?.dismiss() }, 140)
    }

    companion object {
        const val EDIT_TYPE = "EDIT_TYPE"
        const val POST = "POST"
        const val MYPAGE_COMMENT = "MYPAGE_COMMENT"
        const val COMMUNITY_COMMENT = "POST_COMMENT"
        const val COMMENT_PK = "COMMENT_PK"
        const val POST_PK = "POST_PK"
        const val COMMENT_DATA = "COMMENT_DATA"
    }
}
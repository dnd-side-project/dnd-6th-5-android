package com.fork.spoonfeed.presentation.ui.mypage.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.activityViewModels
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.FragmentBottomDialogMyPageBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.ui.community.view.CommunityFragment
import com.fork.spoonfeed.presentation.ui.communitypost.view.CommunityPostActivity
import com.fork.spoonfeed.presentation.ui.communitypost.view.CommunityPostCreateActivity
import com.fork.spoonfeed.presentation.ui.mypage.viewmodel.MyPageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BottomDialogMyPageFragment(private val postId: Int, private val commentId: String, private val type: String) :
    BaseViewUtil.BaseCategoryBottomDialogFragment<FragmentBottomDialogMyPageBinding>(R.layout.fragment_bottom_dialog_my_page) {
    private val myPageViewModel: MyPageViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun initView() {
        setClickListener()
    }

    private fun setClickListener() {
        with(binding) {
            when (type) {
                MANAGEMENT_POST -> {
                    tvBottomDialogMypageEdit.setOnClickListener {
                        startActivity(Intent(context, CommunityPostCreateActivity::class.java).let {
                            it.putExtra(CommunityPostCreateActivity.POST_ID, postId)
                        })
                        dialog?.dismiss()
                    }
                    tvBottomDialogMypageDelete.setOnClickListener {
                        setHandler()
                        myPageViewModel.deleteMyPost(postId)
                    }
                }
                MANAGEMENT_COMMENT -> {
                    binding.tvBottomDialogMypageEdit.setOnClickListener {
                        startActivity(Intent(context, CommunityPostActivity::class.java).let {
                            it.putExtra(CommunityFragment.POST_PK, postId)
                        })
                        dialog?.dismiss()
                    }
                    tvBottomDialogMypageDelete.setOnClickListener {
                        myPageViewModel.deleteMyComment(postId, commentId)
                        setHandler()
                    }
                }
            }
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
        const val MANAGEMENT_NO_COMMENT = "NO_COMMENT"
        const val MANAGEMENT_POST = "POST"
        const val MANAGEMENT_COMMENT = "COMMENT"
    }
}
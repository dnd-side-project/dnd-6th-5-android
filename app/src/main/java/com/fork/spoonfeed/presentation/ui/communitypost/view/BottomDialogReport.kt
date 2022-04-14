package com.fork.spoonfeed.presentation.ui.communitypost.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.FragmentBottomDialogReportUserBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

@AndroidEntryPoint
class BottomDialogReport :
    BaseViewUtil.BaseCategoryBottomDialogFragment<FragmentBottomDialogReportUserBinding>(R.layout.fragment_bottom_dialog_report_user) {

    private var postPk : Int? = null
    private var commentPk : Int? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun initView() {
        arguments?.getInt(CommunityPostActivity.REPORT_POST_PK).let {
            postPk = if (it == 0) null else it
        }
        arguments?.getInt(CommunityPostActivity.REPORT_COMMENT_PK).let {
            commentPk = if (it == 0) null else it
        }
        setReportClickListener()
    }

    private fun setHandler() {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({ dialog?.dismiss() }, 140)
    }

    private fun setReportClickListener() {
        binding.tvBottomDialogReportUser.setOnClickListener {
            startActivity(
                Intent(requireContext(), UserReportReasonActivity::class.java).apply {
                    postPk?.let {
                        putExtra("postPk", postPk)
                    }
                    commentPk?.let {
                        putExtra("commentPk", commentPk)
                    }
                }
            )
            setHandler()
        }
    }
}

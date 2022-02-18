package com.fork.spoonfeed.presentation.ui.mypage.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.FragmentMyPageBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPageFragment : BaseViewUtil.BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun initView() {
        setOnClickListener()
    }

    private fun setOnClickListener() {
        with(binding) {
            ivMypageMyPost.setOnClickListener {
                startActivity(Intent(requireContext(), MyPostManagementActivity::class.java))
            }
            ivMypageInterestedPolicy.setOnClickListener {
                startActivity(Intent(requireContext(), InterastedPolicyActivity::class.java))
            }
            ivMypageNotice.setOnClickListener {
                startActivity(Intent(requireContext(), NoticeActivity::class.java))
            }
            ivMypageQuestion.setOnClickListener {
                startActivity(Intent(requireContext(), QuestionActivity::class.java))
            }
            ivMypageSecession.setOnClickListener {
                startActivity(Intent(requireContext(), SecessionActivity::class.java))
            }
        }
    }
}

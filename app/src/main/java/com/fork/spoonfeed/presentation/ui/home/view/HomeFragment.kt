package com.fork.spoonfeed.presentation.ui.home.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.FragmentHomeBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.ui.policylist.PolicyListActivity
import com.fork.spoonfeed.presentation.ui.mypage.view.InterastedPolicyActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseViewUtil.BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun initView() {
        initClick()
    }


    private fun initClick() {
        //맞춤정책 뷰로 이동

        //청년정책 살펴보기 뷰로 이동
        binding.ivHomeAllBackgroung.setOnClickListener {
            val intent = Intent(requireContext(), PolicyListActivity::class.java)
            startActivity(intent)
        }
        binding.ivHomeDwellingBackgroung.setOnClickListener {
            val intent = Intent(requireContext(), PolicyListActivity::class.java)
            startActivity(intent)
        }
        binding.ivHomeFinanceBackgroung.setOnClickListener {
            val intent = Intent(requireContext(), PolicyListActivity::class.java)
            startActivity(intent)
        }
        //관심있는정책 살펴보기 뷰로 이동
        binding.ivHomeInterastedPolicyMore.setOnClickListener {
            val intent = Intent(requireContext(), InterastedPolicyActivity::class.java)
            startActivity(intent)
        }
    }
}

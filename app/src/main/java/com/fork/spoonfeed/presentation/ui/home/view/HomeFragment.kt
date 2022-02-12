package com.fork.spoonfeed.presentation.ui.home.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.FragmentHomeBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.ui.mypage.view.InterastedPolicyActivity
import com.fork.spoonfeed.presentation.ui.policylist.PolicyListActivity
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
        with(binding) {
            //맞춤정책 뷰로 이동

            ivHomeAllBackgroung.setOnClickListener {
                val intent = Intent(requireContext(), PolicyListActivity::class.java)
                startActivity(intent)
            }
            ivHomeDwellingBackgroung.setOnClickListener {
                val intent = Intent(requireContext(), PolicyListActivity::class.java)
                startActivity(intent)
            }
            ivHomeFinanceBackgroung.setOnClickListener {
                val intent = Intent(requireContext(), PolicyListActivity::class.java)
                startActivity(intent)
            }
            ivHomeInterastedPolicyMore.setOnClickListener {
                val intent = Intent(requireContext(), InterastedPolicyActivity::class.java)
                startActivity(intent)
            }
        }
    }
}

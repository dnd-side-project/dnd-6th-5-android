package com.fork.spoonfeed.presentation.ui.home.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AlertDialogLayout
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.FragmentHomeBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.ui.mypage.view.InterastedPolicyActivity
import com.fork.spoonfeed.presentation.ui.policylist.view.DetailInfoActivity
import com.fork.spoonfeed.presentation.ui.policylist.view.PolicyListActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseViewUtil.BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun initView() {
        initClick()
        /*  binding.ctlHomeInterastedPolicyList.visibility=View.INVISIBLE
          binding.ctlHomeNoInterastedPolicyList.visibility=View.VISIBLE*/
    }


    private fun initClick() {
        with(binding) {
            //맞춤정책 뷰로 이동

            ivHomeAllBackground.setOnClickListener {
                val intent = Intent(requireContext(), PolicyListActivity::class.java).let {
                    it.putExtra(CATEGORY, ALL)
                }
                startActivity(intent)
            }
            ivHomeDwellingBackground.setOnClickListener {
                val intent = Intent(requireContext(), PolicyListActivity::class.java).let {
                    it.putExtra(CATEGORY, DWELLING)
                }
                startActivity(intent)
            }
            ivHomeFinanceBackground.setOnClickListener {
                val intent = Intent(requireContext(), PolicyListActivity::class.java).let {
                    it.putExtra(CATEGORY, FINANCE)
                }
                startActivity(intent)
            }
            ivHomeInterastedPolicyMore.setOnClickListener {
                val intent = Intent(requireContext(), InterastedPolicyActivity::class.java)
                startActivity(intent)
            }
        }
    }

    companion object {
        const val ALL = "ALL"
        const val DWELLING = "DWELLING"
        const val FINANCE = "FINANCE"
        const val CATEGORY = "CATEGORY"
    }
}

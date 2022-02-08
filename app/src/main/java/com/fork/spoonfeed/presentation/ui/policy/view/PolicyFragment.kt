package com.fork.spoonfeed.presentation.ui.policy.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.FragmentPolicyBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PolicyFragment : BaseViewUtil.BaseFragment<FragmentPolicyBinding>(R.layout.fragment_policy) {

    private val youthCenterUrl = "https://www.youthcenter.go.kr/main.do"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun initView() {
        setClickListener()
    }

    private fun setClickListener() {
        binding.tvPolicyYouthCenterShortcut.setOnClickListener {
            val youthCenterIntent = Intent(Intent.ACTION_VIEW, Uri.parse(youthCenterUrl))
            startActivity(youthCenterIntent)
        }
    }
}

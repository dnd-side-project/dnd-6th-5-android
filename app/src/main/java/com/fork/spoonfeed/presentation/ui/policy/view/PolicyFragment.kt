package com.fork.spoonfeed.presentation.ui.policy.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.FragmentMyPageBinding
import com.fork.spoonfeed.databinding.FragmentPolicyBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PolicyFragment : BaseViewUtil.BaseFragment<FragmentPolicyBinding>(R.layout.fragment_policy) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun initView() {
    }
}

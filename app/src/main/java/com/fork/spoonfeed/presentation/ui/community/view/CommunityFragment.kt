package com.fork.spoonfeed.presentation.ui.community.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.FragmentCommunityBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommunityFragment : BaseViewUtil.BaseFragment<FragmentCommunityBinding>(R.layout.fragment_community) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun initView() {
    }
}

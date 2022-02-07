package com.fork.spoonfeed.presentation.ui.mypage.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.FragmentHomeBinding
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
    }
}

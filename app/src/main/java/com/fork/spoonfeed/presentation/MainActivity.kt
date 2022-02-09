package com.fork.spoonfeed.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.ActivityMainBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.ui.community.view.CommunityFragment
import com.fork.spoonfeed.presentation.ui.home.view.HomeFragment
import com.fork.spoonfeed.presentation.ui.mypage.view.MyPageFragment
import com.fork.spoonfeed.presentation.ui.policy.view.PolicyFragment
import com.fork.spoonfeed.presentation.util.replace
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseViewUtil.BaseAppCompatActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val homeFragment: HomeFragment by lazy { HomeFragment() }
    private val policyFragment: PolicyFragment by lazy { PolicyFragment() }
    private val communityFragment: CommunityFragment by lazy { CommunityFragment() }
    private val myPageFragment: MyPageFragment by lazy { MyPageFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
        replace(homeFragment)
        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        binding.bnvMain.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_main_home -> {
                    replace(homeFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.menu_main_policy -> {
                    replace(policyFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.menu_main_community -> {
                    replace(communityFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.menu_main_mypage -> {
                    replace(myPageFragment)
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }
}
package com.fork.spoonfeed.presentation

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
class MainActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val homeFragment: HomeFragment by lazy { HomeFragment() }
    private val policyFragment: PolicyFragment by lazy { PolicyFragment() }
    private val communityFragment: CommunityFragment by lazy { CommunityFragment() }
    private val myPageFragment: MyPageFragment by lazy { MyPageFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
        initBottomNavigation()

        initMainMenu()
    }

    private fun initMainMenu() {
        binding.bnvMain.selectedItemId = intent.getStringExtra(BOTTOM_MOVE)?.let {
            when (it) {
                BOTTOM_POLICY -> R.id.menu_main_policy
                BOTTOM_COMMUNITY -> R.id.menu_main_community
                BOTTOM_MYPAGE -> R.id.menu_main_mypage
                else -> R.id.menu_main_home
            }
        } ?: R.id.menu_main_home
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

    fun moveToPolicy() {
        replace(policyFragment)
    }

    companion object {
        const val BOTTOM_MOVE = "com.fork.spoonfeed.presentation BOTTOM_MOVE"
        const val BOTTOM_HOME = "com.fork.spoonfeed.presentation BOTTOM_HOME"
        const val BOTTOM_POLICY = "com.fork.spoonfeed.presentation BOTTOM_POLICY"
        const val BOTTOM_COMMUNITY = "com.fork.spoonfeed.presentation BOTTOM_COMMUNITY"
        const val BOTTOM_MYPAGE = "com.fork.spoonfeed.presentation BOTTOM_MYPAGE"
    }
}

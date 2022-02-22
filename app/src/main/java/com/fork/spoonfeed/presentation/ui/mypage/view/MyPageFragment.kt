package com.fork.spoonfeed.presentation.ui.mypage.view

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.FragmentMyPageBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.util.showFloatingDialog
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
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
            ivMypageMyInfo.setOnClickListener {
                startActivity(Intent(requireContext(), MyPageMyInfoActivity::class.java))
            }
            ivMypageMyPost.setOnClickListener {
                startActivity(Intent(requireContext(), MyPostManagementActivity::class.java))
            }
            ivMypageInterestedPolicy.setOnClickListener {
                startActivity(Intent(requireContext(), InterestedPolicyActivity::class.java))
            }
            ivMypageNotice.setOnClickListener {
                startActivity(Intent(requireContext(), NoticeActivity::class.java))
            }
            ivMypageOpenSource.setOnClickListener {
                startActivity(Intent(requireContext(), OssLicensesMenuActivity::class.java))
                OssLicensesMenuActivity.setActivityTitle(getString(R.string.oss_license_title))
            }
            ivMypageQuestion.setOnClickListener {
                startActivity(Intent(requireContext(), QuestionActivity::class.java))
            }
            ivMypageSecession.setOnClickListener {
                startActivity(Intent(requireContext(), SecessionActivity::class.java))
            }
            ivMypageLogout.setOnClickListener {
                showLogOutDoneDialog()
            }
        }
    }

    @SuppressLint("ResourceType")
    private fun showLogOutDoneDialog() {
        val dialog = requireContext().showFloatingDialog(R.layout.dialog_logout)
        val confirmBtn = dialog.findViewById<Button>(R.id.tv_lougout_dialog_confirm)
        val cancelBtn = dialog.findViewById<Button>(R.id.tv_logout_dialog_cancel)

        confirmBtn.setOnClickListener {
            dialog.dismiss()
        }

        cancelBtn.setOnClickListener {
            dialog.dismiss()
        }
    }
}

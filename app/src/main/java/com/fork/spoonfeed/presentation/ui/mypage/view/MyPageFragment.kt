package com.fork.spoonfeed.presentation.ui.mypage.view

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.FragmentMyPageBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.ui.mypage.viewmodel.MyPageViewModel
import com.fork.spoonfeed.presentation.ui.onboarding.view.OnboardingActivity
import com.fork.spoonfeed.presentation.util.showFloatingDialog
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import com.navercorp.nid.oauth.NidOAuthLogin
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPageFragment : BaseViewUtil.BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {
    private val myPageViewModel: MyPageViewModel by viewModels()
    private val termsConditionUrl = "https://first-hare-34f.notion.site/348dc74a840f43dfa3d105bf22ce76d6"
    private val protectUrl = "https://first-hare-34f.notion.site/f8761b93032c4d0b844c2b4ca798d9a5"
    private val personalInfoUrl = "https://first-hare-34f.notion.site/f24764bf4d7e4ae28ea304080f9423d1"


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.myPageViewModel = myPageViewModel
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

            ivMypageTermsCondition.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(termsConditionUrl)))
            }

            ivMypageProtect.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(protectUrl)))
            }

            ivMypagePersonalInfo.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(personalInfoUrl)))
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
            if (com.fork.spoonfeed.data.UserData.platform == "kakao") {
                myPageViewModel.logoutWithKakao()
            } else {
                NidOAuthLogin().logout()
                myPageViewModel.logoutWithNaver()
                dialog.dismiss()
                moveToOnBoardingActivity()
            }
        }

        cancelBtn.setOnClickListener {
            dialog.dismiss()
        }

        myPageViewModel.logoutWithKakaoSuccess.observe(this) { logoutSuccess ->
            if (logoutSuccess) {
                dialog.dismiss()
                moveToOnBoardingActivity()
            }
        }
    }

    private fun moveToOnBoardingActivity() {
        Toast.makeText(requireContext(), "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show()
        startActivity(Intent(requireContext(), OnboardingActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        })
    }
}

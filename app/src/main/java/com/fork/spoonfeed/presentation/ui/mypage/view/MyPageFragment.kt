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
import com.fork.spoonfeed.data.local.database.AppDatabase
import com.fork.spoonfeed.databinding.FragmentMyPageBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.ui.mypage.viewmodel.MyPageViewModel
import com.fork.spoonfeed.presentation.ui.onboarding.view.OnboardingActivity
import com.fork.spoonfeed.presentation.util.showFloatingDialog
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
        setObserver()
        setOnClickListener()
    }

    private fun setObserver() {
        myPageViewModel.logoutWithKakaoSuccess.observe(viewLifecycleOwner) { logoutSuccess ->
            if (logoutSuccess) {
                clearDatabase()
                moveToOnBoardingActivity()
            }
        }
        myPageViewModel.logoutWithNaverSuccess.observe(viewLifecycleOwner) { logoutSuccess ->
            if (logoutSuccess) {
                clearDatabase()
                moveToOnBoardingActivity()
            }
        }
    }
    private fun clearDatabase() {
        CoroutineScope(Dispatchers.IO).launch {
            AppDatabase.getInstance(requireContext()).clearAllTables()
        }
    }

    private fun setOnClickListener() {
        with(binding) {
            ctlMypageMyInfo.setOnClickListener {
                startActivity(Intent(requireContext(), MyPageMyInfoActivity::class.java))
            }
            ctlMypageMyPost.setOnClickListener {
                startActivity(Intent(requireContext(), MyPostManagementActivity::class.java))
            }
            ctlMypageInterestedPolicy.setOnClickListener {
                startActivity(Intent(requireContext(), InterestedPolicyActivity::class.java))
            }
            ctlMypageNotice.setOnClickListener {
                startActivity(Intent(requireContext(), NoticeActivity::class.java))
            }

            ctlMypageTermsCondition.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(termsConditionUrl)))
            }

            ctlMypageProtect.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(protectUrl)))
            }

            ctlMypagePersonalInfo.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(personalInfoUrl)))
            }

            ctlMypageOpenSource.setOnClickListener {
                startActivity(Intent(requireContext(), OssLicensesMenuActivity::class.java))
                OssLicensesMenuActivity.setActivityTitle(getString(R.string.oss_license_title))
            }
            ctlMypageQuestion.setOnClickListener {
                startActivity(Intent(requireContext(), QuestionActivity::class.java))
            }
            ctlMypageSecession.setOnClickListener {
                startActivity(Intent(requireContext(), SecessionActivity::class.java))
            }
            ctlMypageLogout.setOnClickListener {
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
                dialog.dismiss()
            } else {
                myPageViewModel.logoutWithNaver()
                dialog.dismiss()
            }
        }

        cancelBtn.setOnClickListener {
            dialog.dismiss()
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

package com.fork.spoonfeed.presentation.ui.mypage.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import com.fork.spoonfeed.R
import com.fork.spoonfeed.data.UserData
import com.fork.spoonfeed.data.local.database.AppDatabase
import com.fork.spoonfeed.databinding.ActivitySecessionBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.ui.mypage.viewmodel.MyPageViewModel
import com.fork.spoonfeed.presentation.ui.onboarding.view.OnboardingActivity
import com.fork.spoonfeed.presentation.util.setBackBtnClickListener
import com.fork.spoonfeed.presentation.util.showFloatingDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SecessionActivity : BaseViewUtil.BaseAppCompatActivity<ActivitySecessionBinding>(R.layout.activity_secession) {
    private val myPageViewModel: MyPageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
        this.setBackBtnClickListener(binding.ivSecessionBack)
        secessionClickListener()
    }

    private fun secessionClickListener() {
        binding.btnSecession.setOnClickListener {
            showSecessionDoneDialog()
        }
    }

    @SuppressLint("ResourceType")
    private fun showSecessionDoneDialog() {
        val dialog = this.showFloatingDialog(R.layout.dialog_secession)
        val confirmBtn = dialog.findViewById<Button>(R.id.tv_secession_dialog_confirm)
        val cancelBtn = dialog.findViewById<Button>(R.id.tv_secession_dialog_cancel)

        confirmBtn.setOnClickListener {
            if (UserData.platform == "kakao") {
                myPageViewModel.deleteWithKakao()
            } else {
                myPageViewModel.deleteWithNaver()
            }
        }

        cancelBtn.setOnClickListener {
            dialog.dismiss()
        }

        myPageViewModel.deleteWithKakaoSuccess.observe(this) { deleteWithKakaoSuccess ->
            if (deleteWithKakaoSuccess) {
                dialog.dismiss()
                clearDatabase()
                moveToOnBoardingActivity()
            }
        }

        myPageViewModel.deleteWithNaverSuccess.observe(this) { deleteWithKakaoSuccess ->
            if (deleteWithKakaoSuccess) {
                dialog.dismiss()
                clearDatabase()
                moveToOnBoardingActivity()
            }
        }
    }

    private fun moveToOnBoardingActivity() {
        startActivity(Intent(this, OnboardingActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        })
    }

    private fun clearDatabase() {
        CoroutineScope(Dispatchers.IO).launch {
            AppDatabase.getInstance(baseContext).clearAllTables()
        }
    }
}

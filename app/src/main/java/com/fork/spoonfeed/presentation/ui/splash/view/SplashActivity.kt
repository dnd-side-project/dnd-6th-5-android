package com.fork.spoonfeed.presentation.ui.splash.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.viewModels
import com.fork.spoonfeed.BuildConfig
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.ActivitySplashBinding
import com.fork.spoonfeed.presentation.MainActivity
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.ui.onboarding.view.OnboardingActivity
import com.fork.spoonfeed.presentation.ui.splash.viewmodel.SplashViewModel
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.log.NidLog
import com.navercorp.nid.oauth.OAuthLoginCallback
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
        checkAutoLogin()
        setObserver()
    }

    private fun checkAutoLogin() {
        viewModel.getAutoLoginPlatform()
    }

    private fun setObserver() {
        viewModel.autoLoginPlatform.observe(this) {
            when (it) {
                "naver" -> checkNaverLogin()
                "kakao" -> checkKakaoLogin()
                null -> moveOnboarding()
            }
        }
        viewModel.naverLoginSuccessWithName.observe(this) {
            if (it.first) {
                moveMain()
            } else if (!it.first) {
                moveOnboarding()
            }
        }
        viewModel.kakaoLoginSuccessWithName.observe(this) {
            if (it.first) {
                moveMain()
            } else if (!it.first) {
                moveOnboarding()
            }
        }
    }

    private fun checkNaverLogin() {
        NidLog.init()
        NaverIdLoginSDK.initialize(
            this,
            BuildConfig.NAVER_CLIENT_ID,
            BuildConfig.NAVER_CLIENT_SECRET,
            BuildConfig.NAVER_APP_NAME
        )
        val oauthLoginCallback = object : OAuthLoginCallback {
            override fun onError(errorCode: Int, message: String) {
                onFailure(errorCode, message)
            }

            override fun onFailure(httpStatus: Int, message: String) {
                val errorCode = NaverIdLoginSDK.getLastErrorCode().code
                val errorDesc = NaverIdLoginSDK.getLastErrorDescription()
                Log.e("naver login error : ", "errorCode:$errorCode, errorDesc:$errorDesc")
                viewModel.loginWithNaver(null, null)
            }

            override fun onSuccess() {
                val accessToken = NaverIdLoginSDK.getAccessToken() ?: return
                val refreshToken = NaverIdLoginSDK.getRefreshToken() ?: return

                viewModel.loginWithNaver(accessToken, refreshToken)
            }
        }
        NaverIdLoginSDK.authenticate(this, oauthLoginCallback)
    }

    private fun checkKakaoLogin() {
        // TODO 자동로그인 구현 필요
        moveOnboarding()
    }

    private fun moveOnboarding() {
        val intent = Intent(baseContext, OnboardingActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        startNextActivityWithHandling(intent)
    }

    private fun moveMain() {
        val intent = Intent(baseContext, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        startNextActivityWithHandling(intent)
    }

    private fun startNextActivityWithHandling(intent: Intent) {
        Handler(Looper.getMainLooper())
            .postDelayed({
                startActivity(intent)
                finish()
            }, 2000)
    }
}

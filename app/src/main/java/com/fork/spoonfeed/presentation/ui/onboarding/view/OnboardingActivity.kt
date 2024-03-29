package com.fork.spoonfeed.presentation.ui.onboarding.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.fork.spoonfeed.BuildConfig
import com.fork.spoonfeed.R
import com.fork.spoonfeed.data.UserData
import com.fork.spoonfeed.databinding.ActivityOnboardingBinding
import com.fork.spoonfeed.presentation.MainActivity
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.ui.onboarding.view.signup.SignupActivity
import com.fork.spoonfeed.presentation.ui.onboarding.viewmodel.LoginViewModel
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.log.NidLog
import com.navercorp.nid.oauth.NidOAuthLogin
import com.navercorp.nid.oauth.OAuthLoginCallback
import com.navercorp.nid.profile.NidProfileCallback
import com.navercorp.nid.profile.data.NidProfileResponse
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class OnboardingActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivityOnboardingBinding>(R.layout.activity_onboarding) {

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.loginViewModel = loginViewModel
        binding.lifecycleOwner = this
        initView()
    }

    override fun initView() {
        setObserver()
        setClickListener()
    }

    private fun setObserver() {
        loginViewModel.loginSuccess.observe(this) {
            if (it) {
                startMainActivity()
            } else {
                startSignupActivity()
            }
        }
    }

    private fun setClickListener() {
        binding.mbOnboardingNaverLogin.setOnClickListener {
            setNaverLogin()
        }
        binding.mbOnboardingKakaoLogin.setOnClickListener {
            setKakaoLogin()
        }
        binding.tvOnboardingSkip.setOnClickListener {
            startActivity(Intent(baseContext, MainActivity::class.java))
            finish()
        }
    }

    private fun setNaverLogin() {
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
            }

            override fun onSuccess() {
                val accessToken = NaverIdLoginSDK.getAccessToken() ?: return
                val refreshToken = NaverIdLoginSDK.getRefreshToken() ?: return
                Log.e("naver login", accessToken)
                getEmail(accessToken, refreshToken)
            }
        }
        NaverIdLoginSDK.authenticate(this, oauthLoginCallback)
    }

    fun getEmail(accessToken: String, refreshToken: String) {
        NidOAuthLogin().callProfileApi(object : NidProfileCallback<NidProfileResponse> {
            override fun onSuccess(response: NidProfileResponse) {
                loginViewModel.setEmail(response.profile?.email!!)
                loginViewModel.loginWithNaver(accessToken, refreshToken)
            }

            override fun onFailure(httpStatus: Int, message: String) {
                val errorCode = NaverIdLoginSDK.getLastErrorCode().code
                val errorDescription = NaverIdLoginSDK.getLastErrorDescription()
                Log.e("naver login erroe", "errorCode:$errorCode, errorDesc:$errorDescription")
            }

            override fun onError(errorCode: Int, message: String) {
                onFailure(errorCode, message)
            }
        })
    }

    private fun setKakaoLogin() {
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                Log.e("kakao login error", "카카오 로그인 실패", error)
            } else if (token != null) {
                UserApiClient.instance.me { user, error ->
                    val accessToken = token.accessToken
                    val refreshToken = token.refreshToken
                    val email = user?.kakaoAccount?.email ?: ""
                    Log.e("kakao login", token.accessToken)
                    Log.e("kakao login", token.refreshToken)

                    loginViewModel.setEmail(email)
                    loginViewModel.loginWithKakao(accessToken, refreshToken)
                }
            }
        }

        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this@OnboardingActivity)) {
            UserApiClient.instance.loginWithKakaoTalk(this@OnboardingActivity, callback = callback)

        } else {
            UserApiClient.instance.loginWithKakaoAccount(
                this@OnboardingActivity,
                callback = callback
            )
        }
    }

    private fun startSignupActivity() {
        startActivity(Intent(baseContext, SignupActivity::class.java))
    }

    private fun startMainActivity() {
        startActivity(Intent(baseContext, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        })
    }
}

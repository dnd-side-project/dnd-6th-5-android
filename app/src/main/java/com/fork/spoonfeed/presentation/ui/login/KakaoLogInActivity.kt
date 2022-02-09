package com.fork.spoonfeed.presentation.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.ActivityKakaoLogInBinding
import com.fork.spoonfeed.presentation.MainActivity
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient

class KakaoLogInActivity : BaseViewUtil.BaseAppCompatActivity<ActivityKakaoLogInBinding>(R.layout.activity_kakao_log_in) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
        initKakaoLogin()
    }

    fun initKakaoLogin() {

        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->

            if (error != null) {
                Log.e("kakao", "로그인 실패", error)
            } else if (token != null) {
                UserApiClient.instance.me { user, error ->
                    Log.e("kakao", "로그인 성공 ${token.accessToken}")
                    //  val kakaoId = user!!.id
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }

        binding.imgSocialKakaoLogo.setOnClickListener {
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(this@KakaoLogInActivity)) {
                UserApiClient.instance.loginWithKakaoTalk(this@KakaoLogInActivity, callback = callback)

            } else {
                UserApiClient.instance.loginWithKakaoAccount(this@KakaoLogInActivity, callback = callback)
            }
        }
    }
}
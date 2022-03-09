package com.fork.spoonfeed.presentation.ui.mypage.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.ActivityQuestionBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.ui.mypage.viewmodel.MyPageViewModel
import com.fork.spoonfeed.presentation.util.setBackBtnClickListener
import dagger.hilt.android.AndroidEntryPoint
import java.util.regex.Matcher
import java.util.regex.Pattern

@AndroidEntryPoint
class QuestionActivity : BaseViewUtil.BaseAppCompatActivity<ActivityQuestionBinding>(R.layout.activity_question) {
    private val myPageViewModel: MyPageViewModel by viewModels()
    var isValidBtn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        binding.myPageViewModel = myPageViewModel
        binding.questionActivity = this
        initView()
    }

    override fun initView() {
        initFocusEvent()
        setOnClickListener()
        setObserve()
        this.setBackBtnClickListener(binding.ivQuestionBack)
    }

    fun isEmail(email: String): Boolean {
        var isEmailFormat = false
        val regex = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$"
        val pattern = Pattern.compile(regex)
        val matcher = pattern.matcher(email)
        if (matcher.matches()) {
            isEmailFormat = true
        }
        return isEmailFormat
    }


    private fun setOnClickListener() {
        binding.btnQuestion.setOnClickListener {
            val email = binding.etQuestionEmail.text.toString()
            val sentence = binding.etQuestionSentence.text.toString()
            if (isEmail(email)) {
                myPageViewModel.postQuestion(sentence, email)
            } else {
                Toast.makeText(this, "이메일 형식을 다시 확인해주세요", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setObserve() {
        myPageViewModel.postQuestionSuccess.observe(this) { postQuestionSuccess ->
            if (postQuestionSuccess) {
                Toast.makeText(this, "문의가 완료되었습니다.", Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }

    private fun initFocusEvent() {
        binding.apply {
            etQuestionEmail.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    setEmailHasFocus()
                } else if (etQuestionEmail.text.isBlank()) {
                    setEmailNotFocus()
                }
            }
            etQuestionSentence.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    setSentenceHasFocus()
                } else if (etQuestionSentence.text.isBlank()) {
                    setSentenceNotFocus()
                }
            }

            when (etQuestionEmail.text.isBlank()) {
                true -> setEmailNotFocus()
                false -> setEmailHasFocus()
            }

            when (etQuestionSentence.text.isBlank()) {
                true -> setSentenceNotFocus()
                false -> setSentenceHasFocus()
            }

            etQuestionEmail.doAfterTextChanged {
                setBtn()
            }

            etQuestionSentence.doAfterTextChanged {
                setBtn()
            }
        }
    }

    fun setBtn() {
        isValidBtn = binding.etQuestionEmail.length() > 0 && binding.etQuestionSentence.length() > 0
        myPageViewModel.postBtnEnable(isValidBtn)
    }

    private fun setEmailNotFocus() {
        binding.etQuestionEmail.setBackgroundResource(R.drawable.bg_gray02_radius_8dp)
    }

    private fun setEmailHasFocus() {
        binding.etQuestionEmail.setBackgroundResource(R.drawable.bg_gray03_radius_8dp)
    }

    private fun setSentenceNotFocus() {
        binding.etQuestionSentence.setBackgroundResource(R.drawable.bg_gray02_radius_8dp)
    }

    private fun setSentenceHasFocus() {
        binding.etQuestionSentence.setBackgroundResource(R.drawable.bg_gray03_radius_8dp)
    }
}
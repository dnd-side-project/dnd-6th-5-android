package com.fork.spoonfeed.presentation.ui.mypage.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.ActivityQuestionBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.ui.mypage.viewmodel.MyPageViewModel
import com.fork.spoonfeed.presentation.util.setBackBtnClickListener

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
        this.setBackBtnClickListener(binding.ivQuestionBack)
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
        if (binding.etQuestionEmail.length() > 0 && binding.etQuestionSentence.length() > 0) {
            isValidBtn = true
        } else {
            isValidBtn = false
        }
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
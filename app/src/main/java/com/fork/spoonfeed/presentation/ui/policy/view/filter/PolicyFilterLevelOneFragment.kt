package com.fork.spoonfeed.presentation.ui.policy.view.filter

import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.FragmentPolicyFilterLevelOneBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil

class PolicyFilterLevelOneFragment :
    BaseViewUtil.BaseFragment<FragmentPolicyFilterLevelOneBinding>(R.layout.fragment_policy_filter_level_one) {

    private val viewModel: PolicyFilterViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onResume() {
        super.onResume()
        (activity as PolicyFilterActivity).scrollToTop()
    }

    override fun initView() {
        setEditTextSuffix()
        setClickListener()
        setViewModelObserver()
    }

    private fun setEditTextSuffix() {
        binding.etPolicyFilterOneAgeYear.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.setAge(year = getIntFromCharSequence(s))
            }

            override fun afterTextChanged(s: Editable?) {}
        })


        binding.etPolicyFilterOneAgeMonth.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.setAge(month = getIntFromCharSequence(s))
                s?.let { formatStringWithPrefix(it.toString(), binding.etPolicyFilterOneAgeMonth) }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.etPolicyFilterOneAgeDay.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.setAge(day = getIntFromCharSequence(s))
                s?.let { formatStringWithPrefix(it.toString(), binding.etPolicyFilterOneAgeDay) }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun getIntFromCharSequence(data: CharSequence?): Int? {
        return data?.let { if (it.isEmpty()) null else it.toString().toInt() }
    }

    private fun formatStringWithPrefix(input: String, editText: EditText) {
        val formattedText = when (input.length) {
            1 -> {
                "0$input"
            }
            3 -> {
                if (input.startsWith("0")) {
                    input.toInt().toString()
                } else {
                    input.substring(0 until input.lastIndex)
                }
            }
            else -> {
                return
            }
        }
        editText.setText(formattedText)
        editText.setSelection(editText.text.length)
    }

    private fun setClickListener() {
        binding.cgPolicyFilterOneMarriage.setOnCheckedChangeListener { _, checkedId ->
            val marriageStatus = when (checkedId) {
                binding.chipPolicyFilterOneMarriageTrue.id -> true
                binding.chipPolicyFilterOneMarriageFalse.id -> false
                else -> return@setOnCheckedChangeListener
            }

            viewModel.setMarriageStatus(marriageStatus)
        }
        binding.mbPolicyFilterOneNext.setOnClickListener {
            (activity as PolicyFilterActivity).moveToNextLevel(PolicyFilterLevelTwoFragment())
        }
    }

    private fun setViewModelObserver() {
        viewModel.age.observe(viewLifecycleOwner, {
            setNextButton(viewModel.isLevelOneValid())
        })

        viewModel.marriageStatus.observe(viewLifecycleOwner, {
            setNextButton(viewModel.isLevelOneValid())
        })
    }

    private fun setNextButton(isActive: Boolean) {
        val color = if (isActive) R.color.primary_blue else R.color.gray03

        with(binding.mbPolicyFilterOneNext) {
            isEnabled = isActive
            backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, color))
        }
    }
}

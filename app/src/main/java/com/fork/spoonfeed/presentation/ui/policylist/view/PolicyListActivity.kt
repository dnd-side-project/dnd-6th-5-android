package com.fork.spoonfeed.presentation.ui.policylist.view

import android.app.Dialog
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.Window
import android.widget.Button
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.ActivityPolicyListBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil
import com.fork.spoonfeed.presentation.ui.policylist.adapter.PolicyListAdapter
import com.fork.spoonfeed.presentation.ui.policylist.adapter.PolicyListResponseData
import com.fork.spoonfeed.presentation.ui.policylist.viewmodel.PolicyListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PolicyListActivity : BaseViewUtil.BaseAppCompatActivity<ActivityPolicyListBinding>(R.layout.activity_policy_list) {
    private val policyListViewModel: PolicyListViewModel by viewModels()
    private lateinit var policyListAdapter: PolicyListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.policyListViewModel = policyListViewModel
        binding.lifecycleOwner = this
        initView()
    }

    override fun initView() {
        setPolicyListAdapter(
            mutableListOf(
                PolicyListResponseData(1, "주거", "청년 우대 통장", "아이조아아이조아아이조아", "2022.02-0222.02", 2),
                PolicyListResponseData(1, "주거", "청년 우대 통장", "아이조아아이조아아이조아", "2022.02-0222.02", 2),
                PolicyListResponseData(1, "주거", "청년 우대 통장", "아이조아아이조아아이조아", "2022.02-0222.02", 2),
                PolicyListResponseData(1, "주거", "청년 우대 통장", "아이조아아이조아아이조아", "2022.02-0222.02", 2),
                PolicyListResponseData(1, "주거", "청년 우대 통장", "아이조아아이아이조아아이조아아이조아아이조아아이조아아이조아아이조아아이조아아이조아아이조아아이조아아이조아아이조아아이조아아이조아아이조아아이조아아이조아", "2022.02-0222.02", 2),
                PolicyListResponseData(1, "주거", "청년 우대 통장", "아이조아아이조아아이조아", "2022.02-0222.02", 2),
                PolicyListResponseData(1, "주거", "청년 우대 통장", "아이조아아이조아아이조아", "2022.02-0222.02", 2)
            )
        )
        setPolicyListObserve()
        setFilterClickObserve()
        setReWriteClickObserve()
    }

    private fun setPolicyListAdapter(dataList: MutableList<PolicyListResponseData>) {
        policyListAdapter = PolicyListAdapter(dataList) { item ->
            startActivity(Intent(this, DetailInfoActivity::class.java))
        }
        with(binding) {
            rvPolicylist.adapter = policyListAdapter
            rvPolicylist.layoutManager = LinearLayoutManager(this@PolicyListActivity)
        }
    }

    private fun setPolicyListObserve() {
        /*      policyListViewModel.policyList.observe(this) { policyList ->
                  policyListAdapter.submitList(policyList)
              }*/
    }

    private fun setFilterClickObserve() {
        policyListViewModel.isFilterClicked.observe(this) { isFilterClicked ->
            isFilterClicked.let {
                if (isFilterClicked) {
                    showBottomFilterDialog()
                }
            }
        }
    }

    private fun setReWriteClickObserve() {
        policyListViewModel.isReWriteClicked.observe(this) { isReWriteClicked ->
            isReWriteClicked.let {
                if (isReWriteClicked) {
                    showReWriteDialog()
                }
            }
        }
    }

    private fun showBottomFilterDialog() {
        val bottomSheetFragment = BottomDialogFilterFragment()
        bottomSheetFragment.show(
            supportFragmentManager,
            bottomSheetFragment.tag
        )
        policyListViewModel.filterOnClickFalse()
    }

    private fun showReWriteDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
        dialog.setContentView(R.layout.dialog_policy_list_rewrite)
        dialog.setCancelable(false)

        dialog.getWindow()!!.setGravity(Gravity.CENTER)
        dialog.show()

        val confirmBtn = dialog.findViewById<Button>(R.id.tv_dialog_confirm)
        val cancelBtn = dialog.findViewById<Button>(R.id.tv_dialog_cancel)

        confirmBtn.setOnClickListener {
            dialog.dismiss()
        }

        cancelBtn.setOnClickListener {
            dialog.dismiss()
        }
        policyListViewModel.reWriteOnClickFalse()
    }
}
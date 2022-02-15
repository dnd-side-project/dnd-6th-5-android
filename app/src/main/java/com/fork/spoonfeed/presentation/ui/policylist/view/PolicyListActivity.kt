package com.fork.spoonfeed.presentation.ui.policylist.view

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
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
import com.fork.spoonfeed.presentation.util.showFloatingDialog
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

    override fun onResume() {
        super.onResume()
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
        setBackBtnClickListener()
        showSearchDoneDialog()
        setPolicyListObserve()
        setFilterClickObserve()
        setReWriteClickObserve()
    }

    private fun setBackBtnClickListener() {
        binding.ivPolicylistBack.setOnClickListener {
            finish()
        }
    }

    private fun setPolicyListAdapter(dataList: MutableList<PolicyListResponseData>) {
        var policyListAdapter = PolicyListAdapter(dataList) {
            Intent(this, DetailInfoActivity::class.java).apply {
                putExtra("category", it.category)
                putExtra("title", it.title)
                putExtra("id", it.id)
                putExtra("likeCount", it.likeCount)
                startActivity(this)
            }
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
            Log.d("1", "ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡisFilterClickedㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ")
            isFilterClicked.let {
                if (isFilterClicked) {
                    showBottomFilterDialog()
                }
                Log.d("2", "${isFilterClicked.toString()}")
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

    @SuppressLint("ResourceType")
    private fun showSearchDoneDialog() {
        val dialog = this.showFloatingDialog(R.layout.dialog_policy_list_search_done)
        val confirmBtn = dialog.findViewById<Button>(R.id.tv_dialog_search_done_confirm)

        confirmBtn.setOnClickListener {
            dialog.dismiss()
        }
    }

    @SuppressLint("ResourceType")
    private fun showReWriteDialog() {
        val dialog = this.showFloatingDialog(R.layout.dialog_policy_list_rewrite)
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

    private fun showBottomFilterDialog() {
        val bottomSheetFragment = BottomDialogFilterFragment()
        Log.d("3", "${policyListViewModel.isFilterClicked.value.toString()}")
        bottomSheetFragment.show(
            supportFragmentManager,
            bottomSheetFragment.tag
        )
        policyListViewModel.filterOnClickFalse()
        Log.d("4", "${policyListViewModel.isFilterClicked.value.toString()}")
    }
}
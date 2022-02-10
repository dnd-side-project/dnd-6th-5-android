package com.fork.spoonfeed.presentation.ui.policylist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.ActivityPolicyListBindingImpl
import com.fork.spoonfeed.presentation.base.BaseViewUtil

class PolicyListActivity : BaseViewUtil.BaseAppCompatActivity<ActivityPolicyListBindingImpl>(R.layout.activity_policy_list) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
    }
}
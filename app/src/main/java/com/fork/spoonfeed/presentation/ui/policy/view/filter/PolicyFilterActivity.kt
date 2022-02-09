package com.fork.spoonfeed.presentation.ui.policy.view.filter

import android.os.Bundle
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.ActivityPolicyFilterBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil

class PolicyFilterActivity :
    BaseViewUtil.BaseAppCompatActivity<ActivityPolicyFilterBinding>(R.layout.activity_policy_filter) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
    }
}

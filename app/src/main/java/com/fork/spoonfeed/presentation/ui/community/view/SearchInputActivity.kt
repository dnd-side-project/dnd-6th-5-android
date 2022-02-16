package com.fork.spoonfeed.presentation.ui.community.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.ActivitySearchInputBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil

class SearchInputActivity : BaseViewUtil.BaseAppCompatActivity<ActivitySearchInputBinding>(R.layout.activity_search_input) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
    }
}
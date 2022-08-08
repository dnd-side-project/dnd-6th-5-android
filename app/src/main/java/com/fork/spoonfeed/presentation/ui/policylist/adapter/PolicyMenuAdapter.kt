package com.fork.spoonfeed.presentation.ui.policylist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fork.spoonfeed.data.remote.model.policy.ResponsePolicyAllData
import com.fork.spoonfeed.databinding.ItemMenuBinding
import com.fork.spoonfeed.presentation.base.BaseViewUtil


class PolicyMenuAdapter(
    private val rewriteClick: () -> Unit,
    private val filterClick: () -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var binding: ItemMenuBinding

    inner class PolicyMenuViewHolder(private val binding: ItemMenuBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind() {
            binding.tvPolicylistRewrite.setOnClickListener { rewriteClick() }
            binding.tvPolicylistFilter.setOnClickListener { filterClick() }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PolicyMenuViewHolder {
        val binding = ItemMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        this.binding = binding
        setVisibility()
        return PolicyMenuViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PolicyMenuViewHolder).onBind()
    }

    fun setVisibility() {
        with(binding) {
            tvPolicylistRewrite.visibility = View.INVISIBLE
            ivPolicylistEnterBack.visibility = View.INVISIBLE
        }
    }
}

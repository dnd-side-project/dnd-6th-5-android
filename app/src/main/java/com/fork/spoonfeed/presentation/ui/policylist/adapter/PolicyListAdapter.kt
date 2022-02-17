package com.fork.spoonfeed.presentation.ui.policylist.adapter

import android.content.ClipData
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.ItemPolicyListBinding

data class PolicyListResponseData(
    val id: Int,
    val category: String,
    val title: String,
    val sentence: String,
    val deadline: String,
    val likeCount: Int
)

class PolicyListAdapter(
    private val policyList: List<PolicyListResponseData>,
    private val clickListener: (PolicyListResponseData) -> Unit
) : ListAdapter<PolicyListResponseData, PolicyListAdapter.PolicyListViewHolder>(diffUtil) {

    inner class PolicyListViewHolder(private val binding: ItemPolicyListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: PolicyListResponseData) {
            binding.apply {
                tvItemCategory.text = data.category
                tvItemPolicyTitle.text = data.title
                tvItemPolicySentence.text = data.sentence
                tvItemDeadline.text = data.deadline
                tvItemLikeCount.text = data.likeCount.toString()

                if (data.category == "주거") {
                    tvItemCategory.setBackgroundResource(R.drawable.bg_dwelling_blue_radius_4dp)
                }

                ctlItem.setOnClickListener {
                    clickListener(data)
                }
                ivItemLike.setOnClickListener {
                    ivItemLike.toggle()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PolicyListViewHolder {
        val binding = ItemPolicyListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PolicyListViewHolder(binding)
    }

    override fun getItemCount() = policyList.size

    override fun onBindViewHolder(holder: PolicyListViewHolder, position: Int) {
        holder.onBind(policyList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<PolicyListResponseData>() {
            override fun areContentsTheSame(oldItem: PolicyListResponseData, newItem: PolicyListResponseData) =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: PolicyListResponseData, newItem: PolicyListResponseData) =
                oldItem.id == newItem.id
        }
    }
}

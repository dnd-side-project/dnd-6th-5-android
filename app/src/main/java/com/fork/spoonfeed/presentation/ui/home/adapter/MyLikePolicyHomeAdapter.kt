package com.fork.spoonfeed.presentation.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fork.spoonfeed.R
import com.fork.spoonfeed.data.remote.model.user.ResponseUserLikePolicyData
import com.fork.spoonfeed.databinding.ItemInterastedPolicyBinding

class MyLikePolicyAdapter(
    private val clickListener: (ResponseUserLikePolicyData.Data.Policy) -> Unit
) : ListAdapter<ResponseUserLikePolicyData.Data.Policy, MyLikePolicyAdapter.MyLikePolicyHomeViewHolder>(diffUtil) {

    inner class MyLikePolicyHomeViewHolder(private val binding: ItemInterastedPolicyBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseUserLikePolicyData.Data.Policy) {
            binding.apply {
                tvItemPolicyTitle.text = data.name
                tvItemCategory.text = data.category

                if (data.host?.contains("\n") == true) {
                    val splitArray = data.host.split("\n")
                    tvItemPolicyInstitution.text = splitArray[0]
                } else {
                    tvItemPolicyInstitution.text = data.host
                }


                if (data.category == "금융") {
                    tvItemCategory.setBackgroundResource(R.drawable.bg_finance_purple_radius_4dp)
                } else {
                    tvItemCategory.setBackgroundResource(R.drawable.bg_dwelling_blue_radius_4dp)
                }

                root.setOnClickListener {
                    clickListener(data)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyLikePolicyAdapter.MyLikePolicyHomeViewHolder {
        val binding = ItemInterastedPolicyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyLikePolicyHomeViewHolder(binding)
    }

    override fun getItemCount() =
        if (DEFAULT_COUNT < currentList.size) {
            3
        } else {
            currentList.size
        }

    override fun onBindViewHolder(holder: MyLikePolicyAdapter.MyLikePolicyHomeViewHolder, position: Int) { holder.onBind(getItem(position))

    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ResponseUserLikePolicyData.Data.Policy>() {
            override fun areContentsTheSame(oldItem: ResponseUserLikePolicyData.Data.Policy, newItem: ResponseUserLikePolicyData.Data.Policy) =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: ResponseUserLikePolicyData.Data.Policy, newItem: ResponseUserLikePolicyData.Data.Policy) =
                oldItem.policyId == newItem.policyId
        }

        const val DEFAULT_COUNT = 3
    }
}
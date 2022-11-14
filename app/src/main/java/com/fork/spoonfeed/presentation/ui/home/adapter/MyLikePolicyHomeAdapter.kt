package com.fork.spoonfeed.presentation.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fork.spoonfeed.R
import com.fork.spoonfeed.data.remote.model.user.ResponseUserLikePolicyData
import com.fork.spoonfeed.databinding.ItemInterastedPolicyBinding
import com.fork.spoonfeed.presentation.util.SimpleDiffUtil

class MyLikePolicyAdapter(
    private val clickListener: (ResponseUserLikePolicyData.Data.Policy) -> Unit
) : ListAdapter<ResponseUserLikePolicyData.Data.Policy, MyLikePolicyAdapter.MyLikePolicyHomeViewHolder>(SimpleDiffUtil()) {

    private lateinit var inflater: LayoutInflater

    class MyLikePolicyHomeViewHolder(private val binding: ItemInterastedPolicyBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseUserLikePolicyData.Data.Policy, clickListener: (ResponseUserLikePolicyData.Data.Policy) -> Unit) {
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

                root.setOnClickListener { clickListener(data) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyLikePolicyHomeViewHolder {
        if (!::inflater.isInitialized)
            inflater = LayoutInflater.from(parent.context)
        val binding = ItemInterastedPolicyBinding.inflate(inflater, parent, false)

        return MyLikePolicyHomeViewHolder(binding)
    }

    override fun getItemCount() =currentList.size

    override fun onBindViewHolder(holder: MyLikePolicyHomeViewHolder, position: Int) {
        holder.onBind(getItem(position), clickListener)

    }
}
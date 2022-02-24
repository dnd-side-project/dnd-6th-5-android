package com.fork.spoonfeed.presentation.ui.community.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fork.spoonfeed.R
import com.fork.spoonfeed.data.remote.model.community.ResponsePostAllData
import com.fork.spoonfeed.databinding.ItemPostBinding

class PostAdapter(
    private val user: Boolean,
    private val clickListener: (ResponsePostAllData.Data.Post) -> Unit
) : ListAdapter<ResponsePostAllData.Data.Post, PostAdapter.CommunityViewHolder>(diffUtil) {

    inner class CommunityViewHolder(
        private val binding: ItemPostBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: ResponsePostAllData.Data.Post) {
            binding.apply {
                tvItemCategory.text = data.category
                tvItemPolicyTitle.text = data.title
                tvItemUserName.text = data.author
                tvItemDeadline.text = data.createdAt
                tvItemPolicyExplain.text = data.content
                tvItemCommentCount.text = data.commentCount
                if (data.category == "금융") {
                    tvItemCategory.setBackgroundResource(R.drawable.bg_finance_purple_radius_4dp)
                }
                if (user) {
                    setClickListenerItemPostEdit(binding)
                }
                ctlItem.setOnClickListener {
                    clickListener(data)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommunityViewHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommunityViewHolder(binding)
    }

    override fun getItemCount() = currentList.size

    override fun onBindViewHolder(holder: CommunityViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ResponsePostAllData.Data.Post>() {
            override fun areContentsTheSame(
                oldItem: ResponsePostAllData.Data.Post,
                newItem: ResponsePostAllData.Data.Post
            ) =
                oldItem == newItem

            override fun areItemsTheSame(
                oldItem: ResponsePostAllData.Data.Post,
                newItem: ResponsePostAllData.Data.Post
            ) =
                oldItem.id == newItem.id
        }
    }

    private fun setClickListenerItemPostEdit(binding: ItemPostBinding) {
        with(binding) {
            ivItemPostEdit.visibility = View.VISIBLE
            ivItemPostEdit.setOnClickListener {
                ctlItemPostEditDialog.visibility = View.VISIBLE
                //     activity.window?.addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            }
            tvCommentDialogEdit.setOnClickListener {
                ctlItemPostEditDialog.visibility = View.INVISIBLE
            }
            tvCommentDialogDelete.setOnClickListener {
                ctlItemPostEditDialog.visibility = View.INVISIBLE
            }
        }
    }
}

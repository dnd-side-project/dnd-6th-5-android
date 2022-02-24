package com.fork.spoonfeed.presentation.ui.mypage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fork.spoonfeed.data.remote.model.user.ResponseUserPostData
import com.fork.spoonfeed.databinding.ItemPostBinding


class MyPostAdapter(
    private val clickListener: (ResponseUserPostData.Data.Post) -> Unit
) : ListAdapter<ResponseUserPostData.Data.Post, MyPostAdapter.MyPostViewHolder>(diffUtil) {

    inner class MyPostViewHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseUserPostData.Data.Post) {
            binding.apply {
                tvItemPolicyExplain.text = data.content
                tvItemCategory.text = data.category
                tvItemCreated.text = data.createdAt
                tvItemPolicyTitle.text = data.title
                tvItemCommentCount.text = data.cnt
                root.setOnClickListener {
                    clickListener(data)
                }
            }
            setClickListenerItemPostEdit(binding)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPostAdapter.MyPostViewHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyPostViewHolder(binding)
    }

    override fun getItemCount() = currentList.size

    override fun onBindViewHolder(holder: MyPostAdapter.MyPostViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ResponseUserPostData.Data.Post>() {
            override fun areContentsTheSame(oldItem: ResponseUserPostData.Data.Post, newItem: ResponseUserPostData.Data.Post) =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: ResponseUserPostData.Data.Post, newItem: ResponseUserPostData.Data.Post) =
                oldItem.postId == newItem.postId
        }
    }

    private fun setClickListenerItemPostEdit(binding: ItemPostBinding) {
        with(binding) {
            ivItemPostEdit.setOnClickListener {
                ctlItemPostEditDialog.visibility = android.view.View.VISIBLE
                //     activity.window?.addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            }
            tvCommentDialogEdit.setOnClickListener {
                ctlItemPostEditDialog.visibility = android.view.View.INVISIBLE
            }
            tvCommentDialogDelete.setOnClickListener {
                ctlItemPostEditDialog.visibility = android.view.View.INVISIBLE
            }
        }
    }
}
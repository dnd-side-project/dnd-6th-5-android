package com.fork.spoonfeed.presentation.ui.mypage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fork.spoonfeed.data.remote.model.user.ResponseUserCommentData
import com.fork.spoonfeed.databinding.ItemCommentBinding

class MyCommentAdapter(
    private val clickListener: (ResponseUserCommentData.Data.Comment) -> Unit
) : ListAdapter<ResponseUserCommentData.Data.Comment, MyCommentAdapter.MyCommentViewHolder>(diffUtil) {

    inner class MyCommentViewHolder(private val binding: ItemCommentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseUserCommentData.Data.Comment) {
            binding.apply {
                tvItemCommentSentence.text = data.content
                tvItemCommentCategory.text = data.title
                tvItemCommentWritedData.text = data.createdAt
                ivItemCommentEdit.setOnClickListener {
                    clickListener(data)
                }
            }
            setClickListenerItemPostEdit(binding)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCommentViewHolder {
        val binding = ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyCommentViewHolder(binding)
    }

    override fun getItemCount() = currentList.size

    override fun onBindViewHolder(holder: MyCommentAdapter.MyCommentViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ResponseUserCommentData.Data.Comment>() {
            override fun areContentsTheSame(oldItem: ResponseUserCommentData.Data.Comment, newItem: ResponseUserCommentData.Data.Comment) =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: ResponseUserCommentData.Data.Comment, newItem: ResponseUserCommentData.Data.Comment) =
                oldItem.commentId == newItem.commentId
        }
    }

    private fun setClickListenerItemPostEdit(binding: ItemCommentBinding) {
        with(binding) {
            ivItemCommentEdit.setOnClickListener {
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

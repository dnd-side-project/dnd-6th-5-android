package com.fork.spoonfeed.presentation.ui.mypage.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fork.spoonfeed.data.remote.model.user.ResponseUserCommentData
import com.fork.spoonfeed.databinding.ItemCommentBinding
import com.fork.spoonfeed.presentation.ui.mypage.view.BottomDialogMyPageFragment
import com.fork.spoonfeed.presentation.util.SimpleDiffUtil

class MyCommentAdapter(
    private val supportFragmentManager: FragmentManager,
    private val clickListener: (ResponseUserCommentData.Data.Comment) -> Unit
) : ListAdapter<ResponseUserCommentData.Data.Comment, MyCommentAdapter.MyCommentViewHolder>(SimpleDiffUtil()) {

    inner class MyCommentViewHolder(private val binding: ItemCommentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseUserCommentData.Data.Comment) {
            binding.apply {
                tvItemCommentSentence.text = data.content
                tvItemCommentCategory.text = data.title
                tvItemCommentWritedData.text = data.createdAt

                ivItemCommentEdit.setOnClickListener {
                    showBottomDialog(data.postId, data.commentId)
                }
                root.setOnClickListener {
                    clickListener(data)
                }
            }
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

    private fun showBottomDialog(postId: Int, commentId: String) {
        val bottomSheetFragment = BottomDialogMyPageFragment()
        bottomSheetFragment.arguments = Bundle().apply {
            putInt(BottomDialogMyPageFragment.POST_PK, postId)
            putString(BottomDialogMyPageFragment.COMMENT_PK, commentId)
            putString(BottomDialogMyPageFragment.EDIT_TYPE, BottomDialogMyPageFragment.MYPAGE_COMMENT)
        }
        bottomSheetFragment.show(
            supportFragmentManager,
            bottomSheetFragment.tag
        )
    }
}

package com.fork.spoonfeed.presentation.ui.mypage.adapter

import android.app.Activity
import android.content.Intent
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListPopupWindow
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fork.spoonfeed.R
import com.fork.spoonfeed.data.remote.model.user.ResponseUserCommentData
import com.fork.spoonfeed.data.remote.model.user.ResponseUserPostData
import com.fork.spoonfeed.databinding.ItemCommentBinding
import com.fork.spoonfeed.databinding.ItemPostBinding
import com.fork.spoonfeed.presentation.ui.communitypost.view.CommunityPostCreateActivity
import com.fork.spoonfeed.presentation.ui.mypage.viewmodel.MyPageViewModel
import com.fork.spoonfeed.presentation.util.dpToPx

class MyCommentAdapter(
    private val myPageViewModel: MyPageViewModel,
    private val context: Activity,
    private val clickListener: (ResponseUserCommentData.Data.Comment) -> Unit
) : ListAdapter<ResponseUserCommentData.Data.Comment, MyCommentAdapter.MyCommentViewHolder>(diffUtil) {

    inner class MyCommentViewHolder(private val binding: ItemCommentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseUserCommentData.Data.Comment) {
            binding.apply {
                tvItemCommentSentence.text = data.content
                tvItemCommentCategory.text = data.title
                tvItemCommentWritedData.text = data.createdAt
                ivItemCommentEdit.setOnClickListener {
                    showMenu(binding, data)
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

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ResponseUserCommentData.Data.Comment>() {
            override fun areContentsTheSame(oldItem: ResponseUserCommentData.Data.Comment, newItem: ResponseUserCommentData.Data.Comment) =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: ResponseUserCommentData.Data.Comment, newItem: ResponseUserCommentData.Data.Comment) =
                oldItem.commentId == newItem.commentId
        }
    }

    private fun showMenu(binding: ItemCommentBinding, data: ResponseUserCommentData.Data.Comment) {
        val items = context.resources.getStringArray(R.array.mypage_popup)

        val popupAdapter =
            object : ArrayAdapter<String>(context, R.layout.item_mypage_popup, items) {
                override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                    val view = super.getView(position, convertView, parent)
                    val color = if (position == 0) {
                        R.color.gray04
                    } else {
                        R.color.delete_red
                    }
                    (view as TextView).setTextColor(ContextCompat.getColor(context, color))
                    return view
                }
            }

        val popup = ListPopupWindow(context).apply {
            anchorView = binding.ivItemCommentEdit
            setAdapter(popupAdapter)
            setDropDownGravity(Gravity.NO_GRAVITY)
            width = context.dpToPx(169)
            height = context.dpToPx(112)
            setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.img_comment_bg))
        }

        popup.setOnItemClickListener { _, view, _, _ ->
            if ((view as TextView).text == "수정하기") {
                clickListener(data)
                popup.dismiss()
            } else {
                myPageViewModel.deleteMyComment(data.postId, data.commentId)
                popup.dismiss()
            }
        }
        popup.show()
    }
}

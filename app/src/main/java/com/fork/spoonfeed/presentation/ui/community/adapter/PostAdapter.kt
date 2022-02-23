package com.fork.spoonfeed.presentation.ui.community.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Visibility
import com.fork.spoonfeed.R
import com.fork.spoonfeed.databinding.FragmentMyPostManagementBinding
import com.fork.spoonfeed.databinding.ItemPostBinding
import com.fork.spoonfeed.presentation.ui.communitypost.view.CommunityPostActivity
import com.fork.spoonfeed.presentation.util.showFloatingDialog

data class PostResponseData(
    val id: Int,
    val category: String,
    val title: String,
    val nickname: String,
    val sentence: String,
    val deadline: String,
    val commentCount: Int
)

class PostAdapter(
    private val user: Boolean,
    private val postList: List<PostResponseData>,
    private val clickListener: (PostResponseData) -> Unit
) : ListAdapter<PostResponseData, PostAdapter.CommunityViewHolder>(diffUtil) {

    inner class CommunityViewHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: PostResponseData) {
            binding.apply {
                tvItemCategory.text = data.category
                tvItemPolicyTitle.text = data.title
                tvItemUserName.text = data.nickname
                tvItemDeadline.text = data.deadline
                tvItemPolicyExplain.text = data.sentence
                tvItemCommentCount.text = data.commentCount.toString()
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

    override fun getItemCount() = postList.size

    override fun onBindViewHolder(holder: CommunityViewHolder, position: Int) {
        holder.onBind(postList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<PostResponseData>() {
            override fun areContentsTheSame(oldItem: PostResponseData, newItem: PostResponseData) =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: PostResponseData, newItem: PostResponseData) =
                oldItem.id == newItem.id
        }
    }

    private fun setClickListenerItemPostEdit(binding:ItemPostBinding) {
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

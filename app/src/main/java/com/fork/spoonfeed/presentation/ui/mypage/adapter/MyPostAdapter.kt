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
import com.fork.spoonfeed.data.remote.model.user.ResponseUserPostData
import com.fork.spoonfeed.databinding.ItemPostBinding
import com.fork.spoonfeed.presentation.ui.communitypost.view.CommunityPostCreateActivity
import com.fork.spoonfeed.presentation.ui.communitypost.view.CommunityPostCreateActivity.Companion.POST_ID
import com.fork.spoonfeed.presentation.ui.mypage.viewmodel.MyPageViewModel
import com.fork.spoonfeed.presentation.util.dpToPx

class MyPostAdapter(
    private val myPageViewModel:MyPageViewModel,
    private val context: Activity,
    private val clickListener: (ResponseUserPostData.Data.Post) -> Unit
) : ListAdapter<ResponseUserPostData.Data.Post, MyPostAdapter.MyPostViewHolder>(diffUtil) {

    inner class MyPostViewHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseUserPostData.Data.Post) {
            binding.apply {
                ivItemPostEdit.visibility = View.VISIBLE
                tvItemPolicyExplain.text = data.content
                tvItemCategory.text = data.category
                tvItemCreated.text = data.createdAt
                tvItemPolicyTitle.text = data.title
                tvItemCommentCount.text = data.cnt

                if (data.category == "금융") {
                    tvItemCategory.setBackgroundResource(R.drawable.bg_finance_purple_radius_4dp)
                }else{
                    tvItemCategory.setBackgroundResource(R.drawable.bg_dwelling_blue_radius_4dp)
                }

                root.setOnClickListener {
                    clickListener(data)
                }
                ivItemPostEdit.setOnClickListener {
                    showMenu(binding, data)
                }
            }
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

    private fun showMenu(binding: ItemPostBinding, data: ResponseUserPostData.Data.Post) {
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
            anchorView = binding.ivItemPostEdit
            setAdapter(popupAdapter)
            setDropDownGravity(Gravity.NO_GRAVITY)
            width = context.dpToPx(169)
            height = context.dpToPx(112)
            setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.img_comment_bg))
        }

        popup.setOnItemClickListener { _, view, _, _ ->
            if ((view as TextView).text == "수정하기") {
                val intent = Intent(context, CommunityPostCreateActivity::class.java).let {
                    it.putExtra(POST_ID, data.postId)
                }
                context.startActivity(intent)
                popup.dismiss()
            } else {
                myPageViewModel.deleteMyPost(data.postId)
                popup.dismiss()
            }
        }
        popup.show()
    }
}

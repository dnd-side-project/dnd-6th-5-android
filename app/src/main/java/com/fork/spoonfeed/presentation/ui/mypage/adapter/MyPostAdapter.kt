package com.fork.spoonfeed.presentation.ui.mypage.adapter

import android.app.Activity
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
import com.fork.spoonfeed.presentation.util.dpToPx

class MyPostAdapter(
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

                root.setOnClickListener {
                    clickListener(data)
                }
                ivItemPostEdit.setOnClickListener {
                    showMenu(binding)
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

    private fun showMenu(binding: ItemPostBinding) {
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
            val textColor = if ((view as TextView).text == "수정하기") {
                R.color.gray04
            } else {
                R.color.delete_red
            }

            with(view) {
                text = view.text
                setTextAppearance(R.style.TextView_Heading_Bold_14)
                setTextColor(ContextCompat.getColor(context, textColor))
            }
        }
        popup.show()
    }
}

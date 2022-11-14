package com.fork.spoonfeed.presentation.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fork.spoonfeed.data.remote.model.user.ResponseUserLikePolicyData
import com.fork.spoonfeed.databinding.RvItemBinding
import com.fork.spoonfeed.databinding.RvItemSecondBinding
import com.fork.spoonfeed.presentation.ui.home.viewmodel.HomeViewModel
import com.fork.spoonfeed.presentation.util.SimpleDiffUtil

class MainAdapter(
    private val context: Context,
    private val homeViewModel: HomeViewModel,
    private val viewLifecycleOwner: LifecycleOwner,
    private val clickListener: (ResponseUserLikePolicyData.Data.Policy) -> Unit
) :
    ListAdapter<ResponseUserLikePolicyData.Data.Policy, RecyclerView.ViewHolder>(
        SimpleDiffUtil()
    ) {

    private val viewPool: RecyclerView.RecycledViewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == INTERESTED) {
            val binding = RvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            InterestedViewHolder(
                binding,
                viewPool,
                context,
                clickListener,
                homeViewModel,
                viewLifecycleOwner
            )
        } else {
            val binding =
                RvItemSecondBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            LikeViewHolder(
                binding,
                viewPool,
                context,
                clickListener,
                homeViewModel,
                viewLifecycleOwner
            )
        }
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 1) {
            INTERESTED
        } else {
            LIKE
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {

    }

    companion object {
        private const val INTERESTED = 1
        private const val LIKE = 2
    }
}

class InterestedViewHolder(
    itemView: RvItemBinding,
    viewPool: RecyclerView.RecycledViewPool,
    context: Context,
    clickListener: (ResponseUserLikePolicyData.Data.Policy) -> Unit,
    homeViewModel: HomeViewModel,
    viewLifecycleOwner: LifecycleOwner
) : RecyclerView.ViewHolder(
    itemView.root
) {
    private val subRv = itemView.subRv
    private val adapter = MyLikePolicyAdapter(clickListener)

    init {
        subRv.adapter = adapter
        val layoutManager = LinearLayoutManager(context)
        layoutManager.recycleChildrenOnDetach = true
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        subRv.layoutManager = layoutManager
        subRv.setRecycledViewPool(viewPool)
        homeViewModel.myLikePolicyList.observe(viewLifecycleOwner) { myLikePolicyList ->
            adapter.submitList(myLikePolicyList)
        }
    }
}

class LikeViewHolder(
    itemView: RvItemSecondBinding,
    viewPool: RecyclerView.RecycledViewPool,
    context: Context,
    clickListener: (ResponseUserLikePolicyData.Data.Policy) -> Unit,
    homeViewModel: HomeViewModel,
    viewLifecycleOwner: LifecycleOwner
) : RecyclerView.ViewHolder(
    itemView.root
) {
    private val subRv = itemView.subRv
    private val adapter = MyLikePolicyAdapter(clickListener)

    init {
        subRv.adapter = adapter
        val layoutManager = LinearLayoutManager(context)
        layoutManager.recycleChildrenOnDetach = true
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        subRv.layoutManager = layoutManager
        subRv.setRecycledViewPool(viewPool)
        homeViewModel.myLikePolicyList.observe(viewLifecycleOwner) { myLikePolicyList ->
            adapter.submitList(myLikePolicyList)
        }
    }
}

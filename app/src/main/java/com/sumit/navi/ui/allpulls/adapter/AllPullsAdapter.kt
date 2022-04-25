package com.sumit.navi.ui.allpulls.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sumit.navi.R
import com.sumit.navi.databinding.ItemPullBinding
import com.sumit.navi.models.Pull

class AllPullsAdapter(private val context: Context) :
    RecyclerView.Adapter<AllPullsAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_pull, parent, false)
        )
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemPullBinding.bind(view)
        fun bind(pull: Pull, context: Context) {
            with(binding) {
                tvTitle.text = pull.name
                tvUserName.text = pull.user.userName
                tvCreated.text = pull.createdAt.substringBefore("T")
                if (pull.closedAt != null) {
                    tvClosed.text = pull.closedAt.substringBefore("T")
                } else {
                    tvClosed.text = "Open"
                }
                Glide.with(context).load(pull.user.avatarUrl).into(avatarImg)
            }
        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<Pull>() {
        override fun areItemsTheSame(oldItem: Pull, newItem: Pull): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Pull, newItem: Pull): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }


    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<Pull>) = differ.submitList(list)


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position], context)
    }
}
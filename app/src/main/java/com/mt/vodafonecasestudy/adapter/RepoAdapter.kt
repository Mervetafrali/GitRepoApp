package com.mt.vodafonecasestudy.adapter

import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.mt.vodafonecasestudy.R
import com.mt.vodafonecasestudy.databinding.RepoLayoutAdapterBinding
import com.mt.vodafonecasestudy.model.RepositoriesItem
import com.mt.vodafonecasestudy.ui.fragment.FirstFragment
import com.mt.vodafonecasestudy.ui.fragment.FirstFragmentDirections
import com.mt.vodafonecasestudy.ui.fragment.SecondFragment

class RepoAdapter : RecyclerView.Adapter<RepoAdapter.MyViewHolder>() {
    inner class MyViewHolder(val binding: RepoLayoutAdapterBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<RepositoriesItem>() {
        override fun areItemsTheSame(
            oldItem: RepositoriesItem,
            newItem: RepositoriesItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: RepositoriesItem,
            newItem: RepositoriesItem
        ): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, differCallback)
    var repos: List<RepositoriesItem>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            RepoLayoutAdapterBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = repos[position]
        Log.i("tag", "current:" + currentItem.toString())
        holder.binding.apply {
            name.text = currentItem.name
            imageView.load(currentItem.owner.avatar_url) {
                crossfade(true)
                crossfade(2000)
            }
            repoName.text = currentItem.full_name
        }
        holder.itemView.setOnClickListener {mView->
            val direction = FirstFragmentDirections.actionFirstFragmentToSecondFragment(currentItem)
            mView.findNavController().navigate(direction)

        }
    }

    override fun getItemCount() = repos.size
}
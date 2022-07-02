package com.mt.vodafonecasestudy.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.mt.vodafonecasestudy.databinding.RepoDetailLayoutAdapterBinding
import com.mt.vodafonecasestudy.model.ReposItem
import com.mt.vodafonecasestudy.ui.fragment.RepositoryDetailFragmentDirections

class ReposAdapter: RecyclerView.Adapter<ReposAdapter.MyViewHolder>() {
    inner class MyViewHolder(val binding: RepoDetailLayoutAdapterBinding) :
        RecyclerView.ViewHolder(binding.root)


    private val differCallback=object : DiffUtil.ItemCallback<ReposItem>() {
        override fun areItemsTheSame(oldItem: ReposItem, newItem: ReposItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ReposItem, newItem: ReposItem): Boolean {
            return oldItem == newItem
        }
    }
        private val differ = AsyncListDiffer(this, differCallback)
        var repoDetail: List<ReposItem>
            get() = differ.currentList
            set(value) {
                differ.submitList(value)
            }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposAdapter.MyViewHolder {
            return MyViewHolder(
                RepoDetailLayoutAdapterBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }

        override fun onBindViewHolder(holder: ReposAdapter.MyViewHolder, position: Int) {
            val currentItem = repoDetail[position]
            Log.i("tag", "current:" + currentItem.toString())

            holder.binding.apply {
                repoNameText.text=currentItem.full_name
                ownerUsernameText.text=currentItem.name
                forkCount.text=currentItem.forks_count.toString()
                languageText.text=currentItem.language
                dfBrancNameText.text=currentItem.default_branch
            }
            holder.itemView.setOnClickListener {mView->
                val direction = RepositoryDetailFragmentDirections.actionRepositoryDetailFragment3ToUserFragment3()
                mView.findNavController().navigate(direction)

            }
        }

        override fun getItemCount() = repoDetail.size

}

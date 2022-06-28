package com.mt.vodafonecasestudy.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.mt.vodafonecasestudy.databinding.RepoLayoutAdapterBinding
import com.mt.vodafonecasestudy.model.Repositories
import com.mt.vodafonecasestudy.model.RepositoriesItem

class RepoAdapter: RecyclerView.Adapter<RepoAdapter.RepoViewHolder>() {
inner class RepoViewHolder(val binding:RepoLayoutAdapterBinding):
        RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<RepositoriesItem>(){
        override fun areItemsTheSame(oldItem: RepositoriesItem, newItem: RepositoriesItem): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: RepositoriesItem, newItem: RepositoriesItem): Boolean {
            return oldItem==newItem
        }

    }
    private val differ= AsyncListDiffer(this,differCallback)
    var repo:List<RepositoriesItem>
    get()=differ.currentList
    set(value){
        differ.submitList(value)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        return RepoViewHolder(
            RepoLayoutAdapterBinding.inflate(
                LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val currentItem= repo[position]
        Log.i("tag","current:"+currentItem.toString())
        holder.binding.apply {
            textViewName.text=currentItem.name
            imageView.load(currentItem.owner.avatar_url){
                crossfade(true)
                crossfade(1000)
            }
        }
    }

    override fun getItemCount()=repo.size
}
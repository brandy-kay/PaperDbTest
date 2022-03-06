package com.odhiambodevelopers.paperdbtest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.odhiambodevelopers.paperdbtest.databinding.NoteRowBinding

class NotesAdapter :ListAdapter<Notes,NotesAdapter.MyHolder>(DiffutilCallback) {

    object DiffutilCallback : DiffUtil.ItemCallback<Notes>(){
        override fun areItemsTheSame(oldItem: Notes, newItem: Notes): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Notes, newItem: Notes): Boolean {
            return oldItem.title == newItem.title
        }

    }

    inner class MyHolder(private val binding: NoteRowBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Notes?) {
            binding.tvTitle.text = item?.title
            binding.tvDescription.text = item?.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(NoteRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val item = getItem(position)
         holder.bind(item)
    }
}
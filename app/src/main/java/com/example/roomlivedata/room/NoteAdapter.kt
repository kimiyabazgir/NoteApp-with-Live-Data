package com.example.roomlivedata.room

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.roomlivedata.databinding.ItemNotesBinding
import com.example.roomlivedata.room.db.NoteEntity


class NotesAdapter  : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {
    private lateinit var binding: ItemNotesBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesAdapter.ViewHolder {
        binding = ItemNotesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: NotesAdapter.ViewHolder, position: Int) {
        //getItem from paging DataAdapter
        holder.bind(differ.currentList[position])
        //Not duplicate items
        holder.setIsRecyclable(false)

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.N)
        @SuppressLint("SetTextI18n", "ResourceAsColor")
        fun bind(item: NoteEntity) {
            binding.apply {
                titleTxt.text=item.title
                descTxt.text=item.desc


            }
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<NoteEntity>() {
        override fun areItemsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)
}





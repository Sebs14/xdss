package com.flores.dummydictionary.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.flores.dummydictionary.R
import com.flores.dummydictionary.databinding.ItemWordBinding
import com.flores.dummydictionary.data.model.Word

class WordAdapter : RecyclerView.Adapter<WordAdapter.WordViewHolder>() {

    inner class WordViewHolder(private val binding: ItemWordBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(word: Word) {
            binding.word = word
            binding.executePendingBindings()
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        WordViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_word,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        words?.let {
            holder.bind(it[position])
        }
    }

    override fun getItemCount() = words?.size ?: 0
    private var words: List<Word>? = null

    fun setData(data: List<Word>) {
        words = data
        notifyDataSetChanged()
    }

}
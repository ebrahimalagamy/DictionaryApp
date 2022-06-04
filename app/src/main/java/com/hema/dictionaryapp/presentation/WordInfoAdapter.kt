package com.hema.dictionaryapp.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hema.dictionaryapp.databinding.ItemWordInfoBinding
import com.hema.dictionaryapp.domain.model.WordInfo

class WordInfoAdapter : RecyclerView.Adapter<WordInfoAdapter.WordInfoViewHolder>() {

    var listOfCoins = emptyList<WordInfo>()
        set(value) {
            field = value
//           notifyDataSetChanged()
            notifyItemRangeChanged(0, value.size)
        }

    inner class WordInfoViewHolder(private val binding: ItemWordInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        operator fun invoke(wordInfo: WordInfo) {
            binding.apply {
                wordInfo.apply {
                    tvWordName.text = word
                    tvWordPhonetic.text = phonetic
                    tvWordMeaning.text = meanings.toString()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordInfoViewHolder {
        return WordInfoViewHolder(
            ItemWordInfoBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: WordInfoViewHolder, position: Int) {
        holder(listOfCoins[position])
    }

    override fun getItemCount() = listOfCoins.size
}

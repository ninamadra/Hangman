package com.example.hangman

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AnswerRecyclerAdapter(private var letters: ArrayList<Letter>) : RecyclerView.Adapter<AnswerRecyclerAdapter.LettersViewHolder>() {
    inner class LettersViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(R.id.letter)
        fun bind(letter: Letter) {
            if (letter.revealed) {
                textView.text = letter.letter.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LettersViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.letter_view, parent, false)
        return LettersViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return letters.size
    }

    override fun onBindViewHolder(holder: LettersViewHolder, position: Int) {
        holder.bind(letters[position])
        holder.setIsRecyclable(false)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setLetters(letters: ArrayList<Letter>) {
        this.letters = letters
        notifyDataSetChanged()
    }
}
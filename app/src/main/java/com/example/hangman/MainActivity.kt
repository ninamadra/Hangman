package com.example.hangman

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var answer: Answer
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: AnswerRecyclerAdapter
    private lateinit var imageView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newGame()
    }

    fun onClickButton(view: View) {
        val button = (view as Button)
        answer.check(view.text[0])
        if (answer.hang) {
            when (answer.badGuesses) {
                1 -> imageView.setImageResource(R.drawable.hangman2)
                2 -> imageView.setImageResource(R.drawable.hangman3)
                3 -> imageView.setImageResource(R.drawable.hangman4)
                4 -> imageView.setImageResource(R.drawable.hangman5)
                5 -> imageView.setImageResource(R.drawable.hangman6)
                6 -> imageView.setImageResource(R.drawable.hangman7)
            }
        }
        button.setBackgroundColor(Color.GRAY)
        button.setTextColor(Color.LTGRAY)
        button.isClickable = false
        if(answer.isGameOver()) {
            answer.reveal()
            Toast.makeText(applicationContext, "Game over!", Toast.LENGTH_SHORT).show()
        }
        recyclerAdapter.setLetters(answer.letters)
        if(answer.isGuessed()) {
            Toast.makeText(applicationContext, "You won!", Toast.LENGTH_SHORT).show()

        }

    }

    fun onNewGame(view: View) {
        newGame()


    }

    private fun newGame() {
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        answer = Answer(this)
        recyclerView = findViewById(R.id.answerGrid)
        recyclerView.layoutManager = GridLayoutManager(this, answer.word.length)
        recyclerAdapter = AnswerRecyclerAdapter(answer.letters)
        recyclerView.adapter = this.recyclerAdapter
        imageView = findViewById(R.id.imageView)
    }
}
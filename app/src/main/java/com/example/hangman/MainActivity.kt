package com.example.hangman

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private var answer = Answer()
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: AnswerRecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        recyclerView = findViewById(R.id.answerGrid)
        recyclerView.layoutManager = GridLayoutManager(this, answer.word.length)
        recyclerAdapter = AnswerRecyclerAdapter(answer.letters)
        recyclerView.adapter = recyclerAdapter

    }

    fun onClickButton(view: View) {
        val button = (view as Button)
        answer.check(view.text[0])
        if (answer.hang) {
            //TODO: zmien obrazek
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
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        answer = Answer()
        recyclerView = findViewById(R.id.answerGrid)
        recyclerView.layoutManager = GridLayoutManager(this, answer.word.length)
        recyclerAdapter = AnswerRecyclerAdapter(answer.letters)
        recyclerView.adapter = recyclerAdapter

    }
}
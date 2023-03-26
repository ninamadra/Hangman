package com.example.hangman

import android.content.Context
import kotlin.random.Random


class Answer(private val context: Context) {
    val word = generateWord()
    val letters = generateLetters()
    private var guessed = 0
    var badGuesses = 0
    var hang = false

    private fun generateLetters(): ArrayList<Letter> {
        val letters = ArrayList<Letter>()
        for (char in word.iterator()) {
            letters.add(Letter(char.uppercaseChar()))
        }
        return letters
    }

    private fun generateWord(): String {
        val array = context.resources.getStringArray(R.array.words)
        return array[Random.nextInt(array.size)]
    }

    fun check(guess: Char) {
        var anyGuess = false
        hang = false
        for (letter in letters) {
            if (letter.letter == guess) {
                letter.revealed = true
                guessed++
                anyGuess = true
            }
        }
        if(!anyGuess) {
            badGuesses++
            hang = true
        }
    }

    fun isGuessed(): Boolean {
        if(word.length == guessed) {
            return true
        }
        return false
    }

    fun isGameOver(): Boolean {
        if(badGuesses >= noChances ) {
            return true
        }
        return false
    }

    fun reveal() {
        for (letter in letters) {
            letter.revealed = true
        }
    }

    companion object {
        const val noChances = 6
    }


}
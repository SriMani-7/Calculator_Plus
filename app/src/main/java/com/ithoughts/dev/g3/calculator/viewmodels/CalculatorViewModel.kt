package com.ithoughts.dev.g3.calculator.viewmodels

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel

class CalculatorViewModel(application: Application) : AndroidViewModel(application) {
    var input by mutableStateOf("")
        private set
    var result by mutableStateOf("")
        private set

    fun updateInput(key: String) {
        if (key == "=") calculate()
        else input = when (key) {
            "C" -> ""
            "back" -> if (input.isNotBlank()) input.dropLast(1) else ""
            "." -> if (input.isEmpty()) "0." else if (input.contains(".")) input else input + key
            "+/-" -> if (input.startsWith("-")) input.removePrefix("-") else "-$input"
            in symbols() -> if (input.isNotEmpty() && input.last().isDigit()) input + key else input
            else -> input + key
        }
    }

    private fun calculate() {
        // TODO: Implement calculate function
    }
}

fun symbols() = listOf("+", "-", "*", "/", "%")
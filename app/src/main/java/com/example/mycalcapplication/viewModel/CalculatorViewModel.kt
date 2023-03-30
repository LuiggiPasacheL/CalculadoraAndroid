package com.example.mycalcapplication.viewModel

import androidx.lifecycle.ViewModel
import com.example.mycalcapplication.R
import com.example.mycalcapplication.model.Calculator

class CalculatorViewModel: ViewModel() {

    private val calculator: Calculator = Calculator()
    var operator: String = ""
    var value: String = ""

    fun calculate(): String {
        if (operator == "") {
            calculator.value = value.toDouble()
            return calculator.value.toString()
        }
        when (operator) {
            "+" -> {
                sum(value)
            }
            "-" -> {
                subtract(value)
            }
            "*" -> {
                multiply(value)
            }
            "/" -> {
                divide(value)
            }
        }
        return calculator.value.toString()
    }

    private fun sum(value: String) {
        calculator.sum(value.toDouble())
    }

    private fun subtract(value: String) {
        calculator.subtract(value.toDouble())
    }

    private fun multiply(value: String) {
        calculator.multiply(value.toDouble())
    }

    private fun divide(value: String) {
        calculator.divide(value.toDouble())
    }

}
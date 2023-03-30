package com.example.mycalcapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.example.mycalcapplication.databinding.ActivityMainBinding
import com.example.mycalcapplication.viewModel.CalculatorViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var calculatorViewModel : CalculatorViewModel

    // TODO: Refactor to reduce duplicated code

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        calculatorViewModel = ViewModelProvider(this)[CalculatorViewModel::class.java]

        // Numbers
        binding.btn0.setOnClickListener(::btnNumberAction)
        binding.btn1.setOnClickListener(::btnNumberAction)
        binding.btn2.setOnClickListener(::btnNumberAction)
        binding.btn3.setOnClickListener(::btnNumberAction)
        binding.btn4.setOnClickListener(::btnNumberAction)
        binding.btn5.setOnClickListener(::btnNumberAction)
        binding.btn6.setOnClickListener(::btnNumberAction)
        binding.btn7.setOnClickListener(::btnNumberAction)
        binding.btn8.setOnClickListener(::btnNumberAction)
        binding.btn9.setOnClickListener(::btnNumberAction)

        // Special
        binding.btnDot.setOnClickListener(::btnDotAction)

        // Operations
        binding.btnSum.setOnClickListener(::btnOperatorAction)
        binding.btnSubtract.setOnClickListener(::btnOperatorAction)
        binding.btnMultiply.setOnClickListener(::btnOperatorAction)
        binding.btnDivide.setOnClickListener(::btnOperatorAction)

        binding.btnEquals.setOnClickListener(::btnEqualsAction)
    }

    private fun btnNumberAction(btn: View){
        if (btn is Button) {
            addText(btn.text.toString())
        }
    }

    private fun btnOperatorAction(btn: View){
        if (btn is Button) {
            val operator = btn.text.toString()
            val text = binding.textView.text.toString()

            if (isOperator(text.last())) {
                replaceOperator(operator)
                return
            }
            var value = text
            if (calculatorViewModel.operator != ""){
                value = text.split(calculatorViewModel.operator).last().toString()
            }
            calculatorViewModel.value = value
            var result: String
            try {
                result = calculatorViewModel.calculate()
                calculatorViewModel.operator = operator
                printResult(result)
                addText(operator)
            } catch (e: ArithmeticException) {
                result = e.message.toString()
                clean()
                printResult(result)
            }
        }
    }

    private fun btnEqualsAction(btn: View) {
        if (btn is Button) {
            val operator = ""
            val text = binding.textView.text.toString()

            if (isOperator(text.last())) {
                replaceOperator(operator)
                return
            }

            var value = text
            if (calculatorViewModel.operator != ""){
                value = text.split(calculatorViewModel.operator).last().toString()
            }
            calculatorViewModel.value = value
            var result: String
            try {
                result = calculatorViewModel.calculate()
                calculatorViewModel.operator = operator
                binding.textView.text = result
                printResult(result)
                addText(operator)
            } catch (e: ArithmeticException) {
                result = e.message.toString()
                clean()
                printResult(result)
            }
            printResult(result)
            addText(operator)
        }
    }

    private fun btnDotAction(btn: View){
        if (btn is Button) {
            val dot = btn.text.toString()
            val text = binding.textView.text.toString()

            var value = text
            if (calculatorViewModel.operator != "") {
                value = text.split(calculatorViewModel.operator).last().toString()
            }

            if (value != "" && !value.contains(dot)) {
                addText(dot)
            }
        }
    }

    private fun clean(){
        binding.textView.text = ""
        binding.textView2.text = ""
        calculatorViewModel.operator = ""
    }

    private fun addText(text: String){
        val prevText = binding.textView.text.toString()
        binding.textView.text = prevText + text
    }

    private fun printResult(result: String){
        binding.textView2.text = result
    }

    private fun replaceOperator(operator: String){
        binding.textView.text = binding.textView.text.toString().dropLast(1) + operator
        calculatorViewModel.operator = operator
    }

    private fun isOperator(char: Char): Boolean {
        return char == binding.btnSubtract.text.last() || char == binding.btnSum.text.last()
                || char == binding.btnMultiply.text.last() || char == binding.btnDivide.text.last()
    }
}
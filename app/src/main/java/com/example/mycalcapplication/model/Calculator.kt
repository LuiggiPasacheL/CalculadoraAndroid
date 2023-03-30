package com.example.mycalcapplication.model

class  Calculator {
    var value: Double = 0.0

    fun sum(value: Double){
        this.value = this.value + (value)
    }

    fun subtract(value: Double){
        this.value = this.value - (value)
    }

    fun multiply(value: Double){
        this.value = this.value * value
    }

    fun divide(value: Double){
        if (value != 0.0){
            this.value = this.value / (value)
        }
        else {
            throw ArithmeticException("error")
        }
    }

}
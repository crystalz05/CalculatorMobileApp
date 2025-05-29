package com.tyro.calculatorapp.view_models

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CalculatorViewModel: ViewModel() {

    private val _input = mutableStateOf("")
    private val _result = mutableStateOf("")

    val input: State<String> = _input
    val result: State<String> = _result

    fun onButtonClick(label: String){

        when(label){
            "C" ->{
                _input.value = ""
                _result.value = ""
            }
            "."->{
                if(_input.value.contains(".")|| _input.value.isEmpty()){
                    _input.value = _input.value
                }else{
                    _input.value += label
                }
            }
            "DEL" -> {
                _input.value = _input.value.dropLast(1)
            }
            "=" -> {

                _result.value = evaluateExpression(_input.value).toString()

            } else ->{
                _input.value += label
            }



        }

    }

    fun tokenize(expression: String): List<String> {
        val tokens = mutableListOf<String>()
        var number = ""
        for (char in expression) {
            if (char.isDigit() || char == '.') {
                number += char
            } else {
                if (number.isNotEmpty()) {
                    tokens.add(number)
                    number = ""
                }
                if (char != ' ') {
                    tokens.add(char.toString())
                }
            }
        }
        if (number.isNotEmpty()) tokens.add(number)
        return tokens
    }

    fun evaluateExpression(expression: String): Double {
        val tokens = tokenize(expression.replace("×", "*").replace("÷", "/"))

        var result = tokens[0].toDoubleOrNull() ?: return Double.NaN
        var index = 1

        while (index < tokens.size) {
            val operator = tokens[index]
            val nextNumber = tokens.getOrNull(index + 1)?.toDoubleOrNull() ?: return Double.NaN

            result = when (operator) {
                "+" -> result + nextNumber
                "-" -> result - nextNumber
                "*" -> result * nextNumber
                "/" -> result / nextNumber
                else -> return Double.NaN
            }

            index += 2
        }

        return result
    }
}

